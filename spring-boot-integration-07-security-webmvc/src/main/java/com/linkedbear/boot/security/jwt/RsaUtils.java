package com.linkedbear.boot.security.jwt;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public abstract class RsaUtils {
    
    public static final int DEFAULT_KEY_SIZE = 2048;
    public static final String RSA_SECURT = "websecurity";
    
    /**
     * 根据路径加载公钥
     * @param filename
     * @return
     */
    public static PublicKey getPublicKey(String filename) {
        try {
            ClassPathResource resource = new ClassPathResource(filename);
            byte[] publicKeyBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            byte[] decode = Base64Utils.decode(publicKeyBytes);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (IOException e) {
            throw new SecurityException("加载公钥文件失败！", e);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }
    
    /**
     * 根据路径加载私钥
     * @param filename
     * @return
     */
    public static PrivateKey getPrivateKey(String filename) {
        try {
            ClassPathResource resource = new ClassPathResource(filename);
            byte[] privateKeyBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            byte[] decode = Base64Utils.decode(privateKeyBytes);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (IOException e) {
            throw new SecurityException("加载私钥文件失败！", e);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }
    
    /**
     * 生成公钥、私钥
     * @param publicKeyFilename
     * @param privateKeyFilename
     * @param secret
     */
    public static void generateKey(String publicKeyFilename, String privateKeyFilename, String secret) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = new SecureRandom(secret.getBytes());
            keyPairGenerator.initialize(DEFAULT_KEY_SIZE, secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            
            // 获取公钥并写出
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            publicKeyBytes = Base64.getEncoder().encode(publicKeyBytes);
            writeFile(publicKeyFilename, publicKeyBytes);
            
            // 获取私钥并写出
            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            privateKeyBytes = Base64.getEncoder().encode(privateKeyBytes);
            writeFile(privateKeyFilename, privateKeyBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e.getMessage(), e);
        }
    }
    
    private static void writeFile(String filename, byte[] bytes) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(file.toPath(), bytes);
        } catch (IOException e) {
            throw new SecurityException("保存密钥文件失败！", e);
        }
    }
    
    public static void main(String[] args) {
        generateKey("D:/jwt_rsa.pub", "D:/jwt_rsa", RSA_SECURT);
    }
}
