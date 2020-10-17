package page.rokiapp;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;

import base.BasePage;

import static config.Config.TAG_PACKAGE;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_MainFood extends BasePage{
    UiDevice uiDevice;
    UiScrollable devicePage;
    /**
     *  定义MainFoodActivity控件变量名称以及对应的文字标签
     *  AllSmartRecipes ：  "全部智能菜谱"
     *  RecipeSearch    ：   菜谱搜索框
     *  KitchenRecipe   ：  "灶具菜谱"
     *  OvenRecipe      ：  "烤箱菜谱"
     *  SteamRecipe     ：  "蒸汽炉菜谱"
     *  KitKnowledge    ：  "厨房知识"
     *  dynamic         ：  "关注动态"
     *  MicroRecipe     ：  "微波炉菜谱"
     */
    private UiObject2 AllSmartRecipes;
    private UiObject2 RecipeSearch;
    private UiObject2 KitchenRecipe;
    private UiObject2 OvenRecipe;
    private UiObject2 SteamRecipe;
    private UiObject2 MicroRecipe;
    private UiObject2 KitKnowledge;
    private UiObject2 dynamic;



    public Page_MainFood() throws Exception {
/*        devicePage  =  new UiScrollable(new UiSelector().resourceId("com.robam.roki:id/listview"));
        //设置垂直滚动
        devicePage.setAsVerticalList();
        // 初始化控件对象
        Main_Figure =  WaitForExists(By.res(TAG_PACKAGE, "txtFigure"));*/
    }


    /**
     * 点击-全部智能菜谱-按钮
     * @throws Exception
     */
    public void clickAllSmartRecipes() throws Exception{
        AllSmartRecipes = WaitForExists(By.res(TAG_PACKAGE, "img_classify"));
        AllSmartRecipes.click();
        time(delayTimeMs);
    }

    /**
     * 点击-菜谱搜索-按钮进行搜索
     * @param  cook
     * @throws Exception
     */
    public void clickRecipeSearch(String cook) throws Exception{
        RecipeSearch = WaitForExists(By.res(TAG_PACKAGE, "home_recipe_ll_search"));
        RecipeSearch.click();
        WaitForExists(By.res(TAG_PACKAGE, "edtSearch")).setText(cook);
        WaitForExists(By.res(TAG_PACKAGE, "txtCanel")).click();
        WaitForExists(By.res(TAG_PACKAGE, "edtSearch")).clear();
        time(1000);
    }

    /**
     * 点击-灶具菜谱-按钮
     * @throws Exception
     */
    public void clickKitchenRecipe() throws Exception{
        KitchenRecipe = WaitForExists(By.text("灶具菜谱"));
        KitchenRecipe.click();
        time(delayTimeMs);
    }

    /**
     * 点击-烤箱菜谱-按钮
     * @throws Exception
     */
    public void clickOvenRecipe() throws Exception{
        OvenRecipe = WaitForExists(By.text("烤箱菜谱"));
        OvenRecipe.click();
        time(delayTimeMs);
    }

    /**
     * 点击-蒸汽炉菜谱-按钮
     * @throws Exception
     */
    public void clickSteamRecipe() throws Exception{
        SteamRecipe = WaitForExists(By.text("蒸汽炉菜谱"));
        SteamRecipe.click();
        time(delayTimeMs);
    }


    /**
     * 点击-微波炉菜谱-按钮
     * @throws Exception
     */
    public void clickMicroRecipe() throws Exception{
        MicroRecipe = WaitForExists(By.text("蒸汽炉菜谱"));
        MicroRecipe.click();
        time(delayTimeMs);
    }


    /**
     * 点击-厨房知识-按钮
     * @throws Exception
     */
    public void clickKitKnowledge() throws Exception{
        KitKnowledge = WaitForExists(By.res(TAG_PACKAGE, "home_recipe_iv_chufang_zhishi"));
        KitKnowledge.click();
        time(delayTimeMs);
    }

    /**
     * 点击-关注动态-按钮
     * @throws Exception
     */
    public void clickDynamic() throws Exception{
        dynamic = WaitForExists(By.res(TAG_PACKAGE, "home_recipe_imgv_dynamic"));
        dynamic.click();
        time(delayTimeMs);
    }

}

