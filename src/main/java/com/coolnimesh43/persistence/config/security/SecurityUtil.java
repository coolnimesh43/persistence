package com.coolnimesh43.persistence.config.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    private final static SecureRandom sr = new SecureRandom();
    private static MessageDigest pdigest;
    static {
        try {
            pdigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            pdigest = null;
        }
    }

    private SecurityUtil() {
    }

    /**
     * Get the login of the current user.
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    // /**
    // * Check if a user is authenticated.
    // *
    // * @return true if the user is authenticated, false otherwise
    // */
    // public static boolean isAuthenticated() {
    // SecurityContext securityContext = SecurityContextHolder.getContext();
    // Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
    // if (authorities != null) {
    // for (GrantedAuthority authority : authorities) {
    // if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
    // return false;
    // }
    // }
    // }
    // return true;
    // }

    /**
     * Return the current user, or throws an exception, if the user is not authenticated yet.
     *
     * @return the current user
     */
    public static User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                return (User) authentication.getPrincipal();
            }
        }
        throw new IllegalStateException("User not found!");
    }

    /**
     * If the current user has a specific authority (security role).
     *
     * <p>
     * The name of this method comes from the isUserInRole() method in the Servlet API
     * </p>
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
            }
        }
        return false;
    }

    public static String encodePassword(String password, String salt) {
        try {
            BigInteger val = new BigInteger(salt);
            return getDigest((MessageDigest) pdigest.clone(), password, val.toByteArray()).toString();
        } catch (CloneNotSupportedException e) {
        }

        return password;
    }

    public static BigInteger getDigest(MessageDigest digest, String password, byte[] salt) {
        byte[] output = digest.digest(password.getBytes());
        digest.update(salt); // how is salt generated
        digest.update(output);
        return new BigInteger(1, digest.digest());
    }

    public static String generateSecureSalt() {
        byte[] salt = new byte[128];
        return getSecureRandom(salt);
    }

    public static String generateRandomValue(final int len) {
        byte[] salt = new byte[len];
        return getSecureRandom(salt);
    }

    private static String getSecureRandom(byte[] salt) {
        synchronized (sr) {
            sr.nextBytes(salt);
        }
        return new BigInteger(salt).abs().toString();
    }

    public static List<String> getCurrentUserAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream().map(a -> {
                return a.getAuthority();
            }).collect(Collectors.toList());
        }
        throw new IllegalStateException("User not found");
    }
}
