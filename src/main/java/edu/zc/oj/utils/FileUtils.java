package edu.zc.oj.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailTo:5239604@qq.com">coderPlus-tr</a>
 * @date 2021/3/6
 */
@Slf4j
public class FileUtils {
    private static final UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
    public static void setOwnerAndGroup(String filePath, String owner, String group){
        try {
            final UserPrincipal userPrincipal = lookupService.lookupPrincipalByName(owner);
            final GroupPrincipal groupPrincipal = lookupService.lookupPrincipalByGroupName(group);
            PosixFileAttributeView view = Files.getFileAttributeView(Paths.get(filePath), PosixFileAttributeView.class);
            view.setOwner(userPrincipal);
            view.setGroup(groupPrincipal);

        } catch (IOException e) {
            log.error("user:{} or group: {}  not exists", owner, group);
        }
    }
    public static void setPermission(String filePath, Set<PosixFilePermission> permissions){
        if(permissions == null){
            permissions = new HashSet<>(5);
            permissions.add(PosixFilePermission.OWNER_EXECUTE);
            permissions.add(PosixFilePermission.OWNER_WRITE);
            permissions.add(PosixFilePermission.OWNER_READ);
            permissions.add(PosixFilePermission.GROUP_EXECUTE);
            permissions.add(PosixFilePermission.OTHERS_EXECUTE);
        }
        try {
            Files.setPosixFilePermissions(Paths.get(filePath), permissions);
        } catch (IOException e) {
            log.error("set file<{}> permission error cause: {}", filePath, e.getMessage());
        }
    }
    public static  void setPermission(String filepath){
        setPermission(filepath, null);
    }
}
