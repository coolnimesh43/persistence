package com.coolnimesh43.persistence.config.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ExceptionHandlingAsyncTaskExecutor implements AsyncTaskExecutor, InitializingBean, DisposableBean {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AsyncTaskExecutor asyncTaskExecutor;

    public ExceptionHandlingAsyncTaskExecutor(AsyncTaskExecutor asyncTaskExecutor) {
        super();
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Override
    public void execute(Runnable task) {
        asyncTaskExecutor.execute(this.createWrappedRunnable(task));
    }

    @Override
    public void execute(Runnable task, long startTimeOut) {
        this.asyncTaskExecutor.execute(this.createWrappedRunnable(task), startTimeOut);
    }

    private void runTaskWithContext(final Runnable task) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            SecurityContext ctx = SecurityContextHolder.createEmptyContext();
            ctx.setAuthentication(authentication);
            SecurityContextHolder.setContext(ctx);
            task.run();
        } catch (Exception e) {
            handle(e);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private Runnable createWrappedRunnable(final Runnable task) {
        return () -> {
            this.runTaskWithContext(task);
        };
    }

    private <T> Callable<T> createCallable(final Callable<T> task) {
        return () -> {
            try {
                final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                SecurityContext ctx = SecurityContextHolder.createEmptyContext();
                ctx.setAuthentication(authentication);
                SecurityContextHolder.setContext(ctx);
                return task.call();
            } catch (Exception e) {
                handle(e);
                throw e;
            } finally {
                SecurityContextHolder.clearContext();
            }
        };
    }

    protected void handle(Exception e) {
        log.error("Caught async exception", e);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return this.asyncTaskExecutor.submit(this.createWrappedRunnable(task));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return this.asyncTaskExecutor.submit(this.createCallable(task));
    }

    @Override
    public void destroy() throws Exception {
        if (this.asyncTaskExecutor instanceof DisposableBean) {
            DisposableBean bean = (DisposableBean) this.asyncTaskExecutor;
            bean.destroy();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.asyncTaskExecutor instanceof InitializingBean) {
            InitializingBean bean = (InitializingBean) this.asyncTaskExecutor;
            bean.afterPropertiesSet();
        }

    }

}
