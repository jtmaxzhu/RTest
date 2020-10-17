package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import testcase.pad.uitest.RokiPad_center;
import testcase.pad.uitest.RokiPad_fan;
import testcase.pad.uitest.RokiPad_login;
import testcase.pad.uitest.RokiPad_recipe;
import testcase.pad.uitest.RokiPad_smart_5916s;
import testcase.pad.uitest.RokiPad_wifi;


/**
 * Created by Administrator on 2018/8/11 0011.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RokiPad_login.class,
        RokiPad_smart_5916s.class,
        RokiPad_fan.class,
        RokiPad_center.class,
        RokiPad_recipe.class,
        RokiPad_wifi.class,
})
public class AllTest {
}
