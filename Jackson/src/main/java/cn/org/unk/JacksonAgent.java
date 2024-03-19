package cn.org.unk;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class JacksonAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {

        inst.addTransformer(new JacksonClassFileTransformer(),true);
        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        for (Class loadedClass : allLoadedClasses) {
            if("com.fasterxml.jackson.databind.node.BaseJsonNode".equals(loadedClass.getName())){
                //重新transform
                inst.retransformClasses(loadedClass);
            }
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {

        inst.addTransformer(new JacksonClassFileTransformer(),true);
        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        for (Class loadedClass : allLoadedClasses) {
            if("com.fasterxml.jackson.databind.node.BaseJsonNode".equals(loadedClass.getName())){
                //重新transform
                inst.retransformClasses(loadedClass);
            }
        }
    }
}
