package cn.org.unk;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;

import java.security.ProtectionDomain;

public class JacksonClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer){

        String target1 = "com.fasterxml.jackson.databind.node.BaseJsonNode";

        String className2 = className.replace("/", ".");
        if (className2.equals(target1)) {
            System.out.println("Find the Inject Class: "+target1);
            ClassPool pool = ClassPool.getDefault();
            try {

                CtClass c = pool.getCtClass(className2);
                System.out.println("hhhh");
                CtMethod ctMethod = c.getDeclaredMethod("writeReplace");

                c.removeMethod(ctMethod);
                byte[] bytes = c.toBytecode();
                c.detach();
                return bytes;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

}
