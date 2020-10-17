package framework.core;

import android.support.test.uiautomator.UiDevice;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.File;

import lib.ComUtil;
import lib.LogUtil;
import lib.MethodDescription;


/**
 * Created by Administrator on 2018/7/30.
 * 该类用于配置junit用例失败重新运行
 */

public class RetryRule implements TestRule{
    private final int retryCount;
    //构造方法
    public RetryRule(int retryCount){
        this.retryCount=retryCount;
    }

    public UiDevice mDevice = ComUtil.getInstance().getDevice();

    @Override
    public Statement apply(Statement base, Description description) {
        return statement(base,description);
    }

    private Statement statement(final Statement base, final Description description){
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String className = description.getClassName();
                String methodName = description.getMethodName();
                String desc = description.getTestClass().getDeclaredMethod(methodName).getAnnotation(MethodDescription.class).value();
                Throwable caughtThrowable=null;
                for(int i=0;i<retryCount;i++){
                    try {
                        LogUtil.d("LogRTest",desc+":in");
                        base.evaluate();
                        return;
                    }catch (final Throwable t){
                        caughtThrowable = t;
                        //mDevice.takeScreenshot(new File("/sdcard/temp/err1/"+className+"_"+methodName+".jpg"));
                        LogUtil.d("LogRTest",desc +":运行第"+(i+1)+"次失败");
                    }finally {
                        LogUtil.d("LogRTest",desc+":退出");
                    }
                }
                LogUtil.d("LogRTest",description.getDisplayName()+":重试"+retryCount+"次失败");
                mDevice.takeScreenshot(new File("/sdcard/temp/"+desc+"_err"+".png"));
                assert caughtThrowable != null;
                throw caughtThrowable;
            }

        };

 }
}
