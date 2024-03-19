# Java-useful-agents



CTF java题中常用的javaagent。

每份agent都有两个版本：agentmain和premain，前者用于运行时动态加载，后者用于-javaagent参数。

编译好的agent在target目录下。



## Jackson

移除`BaseJsonNode#writeReplace`。

## AbstractAction

修改`javax.swing.AbstractAction#writeObject`，用于gadget: readObject->toString。









