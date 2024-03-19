package cn.org.unk;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AbstractActionAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {

        inst.addTransformer(new AbstractActionClassFileTransformer(),true);
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class loadedClass : allLoadedClasses) {
            if("javax.swing.AbstractAction".equals(loadedClass.getName())){
                inst.retransformClasses(loadedClass);
            }
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {

        inst.addTransformer(new AbstractActionClassFileTransformer(),true);
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class loadedClass : allLoadedClasses) {
            if("javax.swing.AbstractAction".equals(loadedClass.getName())){
                inst.retransformClasses(loadedClass);
            }
        }
    }
}
