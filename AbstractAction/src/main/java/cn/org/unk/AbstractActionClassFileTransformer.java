package cn.org.unk;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class AbstractActionClassFileTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer){

        String target1 = "javax.swing.AbstractAction";

        String className2 = className.replace("/", ".");
        if (className2.equals(target1)) {
            System.out.println("Find the Inject Class: "+target1);
            ClassPool pool = ClassPool.getDefault();
            try {
                CtClass c = pool.getCtClass(className2);
                CtMethod ctMethod = c.getDeclaredMethod("writeObject");
                ctMethod.setBody("{" +
                        "$1.defaultWriteObject();" +
                        "System.out.println(arrayTable.get(\"sad\"));" +
                        "$1.writeInt(2);" +
                        "$1.writeObject(\"11\");" +
                        "$1.writeObject(arrayTable.get(\"11\"));" +
                        "$1.writeObject(\"11\");" +
                        "$1.writeObject(arrayTable.get(\"22\"));" +
                        "}");

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
