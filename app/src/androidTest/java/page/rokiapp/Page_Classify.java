package page.rokiapp;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by Administrator on 2018/9/3.
 */

public class Page_Classify {

    /**
     *  定义ClassifyActivity控件变量名称以及对应的文字标签
     *  Hot    ：  "热门"
     *  Food   ：  "食材"
     *  Cook   ：  "菜系"
     *  Kitchen：  "厨电"
     *  Taste  ：  "口味"
     */

    private UiObject2 Hot;
    private UiObject2 Food;
    private UiObject2 Cook;
    private UiObject2 Kitchen;
    private UiObject2 Taste;


    public Page_Classify() throws Exception{
        // 初始化控件对象
        Hot     = WaitForExists(By.text("热门"));
        Food    = WaitForExists(By.text("食材"));
        Cook    = WaitForExists(By.text("菜系"));
        Kitchen = WaitForExists(By.text("厨电"));
        Taste   = WaitForExists(By.text("口味"));
    }

    /**
     * 点击-热门-按钮
     * @throws Exception
     */
    public void clickHot() throws Exception{
        Hot.click();
        time(delayTimeMs);
    }

    /**
     * 点击-食材-按钮
     * @throws Exception
     */
    public void clickCook() throws Exception{
        Cook.click();
        time(delayTimeMs);
    }

    /**
     * 点击-厨电-按钮
     * @throws Exception
     */
    public void clickKitchen() throws Exception{
        Kitchen.click();
        time(delayTimeMs);
    }

    /**
     * 点击-口味-按钮
     * @throws Exception
     */
    public void clickTaste() throws Exception{
        Taste.click();
        time(delayTimeMs);
    }

    /**
     * 点击-菜系-按钮
     * @throws Exception
     */
    public void clickFood() throws Exception{
        Food.click();
        time(delayTimeMs);
    }





}
