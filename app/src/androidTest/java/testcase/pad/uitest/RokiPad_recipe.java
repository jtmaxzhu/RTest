package testcase.pad.uitest;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import base.rokipad.BaseTest;
import lib.MethodDescription;
import lib.perftool.CmdTools;
import page.rokipad.Page_fan;
import page.rokipad.Page_leftbar;
import page.rokipad.Page_recipe;
import page.rokipad.Page_rightbar;
import page.rokipad.Page_wifi;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.time;


/**
 * Created by Administrator on 2018/9/2
 * 菜谱相关的测试用例
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RokiPad_recipe extends BaseTest {

    private static final String hintErr = "请输入关键字";
    private static final String hintResult = "抱歉，未找到您搜索的菜谱";
    private String hint = "缺少智能设备，无法开始自动烹饪";


    @Before
    @MethodDescription("环境初始化")
    public void init() throws Exception {
        if (!mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"recipe")),1000)){
            if (mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"search_btn")),1000)){
                Page_leftbar leftbar = new Page_leftbar();
                leftbar.clickHome();
            }else {
                //点击侧边栏登录按钮
                Page_leftbar leftbar = new Page_leftbar();
                leftbar.clickHome();
                Page_fan fan = new Page_fan();
                fan.clickRecipe();
            }

        }else {
            Page_fan fan = new Page_fan();
            fan.clickRecipe();
        }
    }


    /**
     * 1、进入菜谱页面
     * 2、搜索红烧肉
     * 3、验证搜索结果是否正常
     * 4、下面模块逻辑重复1-3
     * @throws Exception
     */
    @Test
    @MethodDescription("搜索菜谱")
    public void Test02_SearchRecipe() throws Exception {
        Page_recipe recipe = new Page_recipe();
        recipe.setRecipe("红烧肉");
        recipe.clickRecipeSearch();
        Assert.assertTrue(recipe.checkRecipe("红烧肉"));
        time(delayTimeMs);


        recipe.setRecipe("牛肉");
        recipe.clickRecipeSearch();
        Assert.assertTrue(recipe.checkRecipe("牛肉"));
        time(delayTimeMs);

        recipe.setRecipe("豆腐");
        recipe.clickRecipeSearch();
        Assert.assertTrue(recipe.checkRecipe("豆腐"));
        time(delayTimeMs);

        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickBack();
        Page_fan fan = new Page_fan();
        fan.clickRecipe();
    }

    /**
     * 1、搜索框为空
     * 2、验证结果是否符合预期，应该提示菜谱不存在
     * @throws Exception
     */
    @Test
    @MethodDescription("搜索空菜谱")
    public void Test01_SearchNullRecipe() throws Exception {
        Page_recipe recipe = new Page_recipe();
        //recipe.setRecipe("");
        recipe.clickRecipeSearch();
        Assert.assertTrue("未发现"+ hintErr +"提示信息!",  processToast(hintErr));
        time(delayTimeMs);
    }


    /**
     * 1、搜索一个不存在的菜谱
     * 2、验证给出的提示是否正确
     * @throws Exception
     */
    @Test
    @MethodDescription("搜索菜谱不存在")
    public void Test03_SearchNoRecipe() throws Exception {
        Page_recipe recipe = new Page_recipe();
        recipe.setRecipe("电");
        recipe.clickRecipeSearch();
        Assert.assertEquals(hintResult,  recipe.getSearchResult());
        time(delayTimeMs);
    }


    /**
     * 1、点击历史搜索记录的菜谱标签
     * 2、验证搜索结果是否符合预期
     *
     * @throws Exception
     */
    @Test
    @MethodDescription("使用历史记录搜索菜谱")
    public void Test04_SearchRecipeUseRecord() throws Exception {
        Page_recipe recipe = new Page_recipe();
        List<UiObject2> obj = recipe.getObj();
        //点击并返回历史搜索记录第一条，验证搜索结果是否正常
        obj.get(0).click();
        Assert.assertTrue(recipe.checkRecipe(obj.get(0).getText()));
        time(delayTimeMs);
        //点击并返回历史搜索记录第二条，验证搜索结果是否正常
        obj.get(1).click();
        Assert.assertTrue(recipe.checkRecipe(obj.get(1).getText()));
        time(delayTimeMs);
        //点击并返回历史搜索记录第三条，验证搜索结果是否正常
        obj.get(2).click();
        Assert.assertTrue(recipe.checkRecipe(obj.get(2).getText()));
        time(delayTimeMs);
    }


    /**
     * 1、依次点击菜谱右侧的选项卡
     * 2、验证各组内部菜谱显示是否正常（菜谱名称来判断）
     * @throws Exception
     */
    @Test
    @MethodDescription("验证菜谱加载正确性")
    public void Test05_RecipeRecom() throws Exception {
        Page_recipe recipe = new Page_recipe();
        //验证菜谱是否显示目标菜，前三个
        Assert.assertTrue(recipe.checkRecipeIsExist("葱姜基围虾"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("可乐鸡翅"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("奶油蛋糕卷"));
        time(delayTimeMs);

        //切换到灶具菜谱选项卡
        recipe.clickRecipeZJ();
        Assert.assertTrue(recipe.checkRecipeIsExist("生煎鱼饼"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("葱油鱼片"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("番茄炒蛋"));
        time(delayTimeMs);

        //切换到无人锅菜谱选项卡
        recipe.clickRecipeWRG();
        Assert.assertTrue(recipe.checkRecipeIsExist("无锡排骨"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("羊肉冬瓜汤"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("番茄焖排骨"));
        time(delayTimeMs);

        //切换到电烤箱菜谱选项卡
        recipe.clickRecipeDKX();
        Assert.assertTrue(recipe.checkRecipeIsExist("香柠烤辣翅"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("迷迭香烤羊排"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("奶酪焗海鲜"));
        time(delayTimeMs);

        //切换到电蒸箱菜谱选项卡
        recipe.clickRecipeDZX();
        Assert.assertTrue(recipe.checkRecipeIsExist("清蒸大闸蟹"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("蛏子蒸粉丝"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("清蒸小鲍鱼"));
        time(delayTimeMs);

        //切换到微波炉菜谱选项卡
        recipe.clickRecipeWBL();
        Assert.assertTrue(recipe.checkRecipeIsExist("蒜薹肚条"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("莲藕排骨"));
        time(delayTimeMs);
        Assert.assertTrue(recipe.checkRecipeIsExist("豆瓣桂鱼"));
        time(delayTimeMs);
    }


    /**
     * 1、依次点击菜谱各选项卡
     * 2、菜谱显示列表左滑到不能滑动位置
     * 3、右滑到不能滑动为止
     * 4、验证是否出现异常
     * @throws Exception
     */
    @Test
    @MethodDescription("验证菜谱左右滑动加载")
    public void Test06_RecipeSwip() throws Exception {
        Page_recipe recipe = new Page_recipe();
        recipe.swipRecipe();

        //切换到灶具菜谱选项卡
        recipe.clickRecipeZJ();
        recipe.swipRecipe();
        time(delayTimeMs);

        //切换到无人锅菜谱选项卡
        recipe.clickRecipeWRG();
        recipe.swipRecipe();
        time(delayTimeMs);

        //切换到电烤箱菜谱选项卡
        recipe.clickRecipeDKX();
        recipe.swipRecipe();
        time(delayTimeMs);

        //切换到电蒸箱菜谱选项卡
        recipe.clickRecipeDZX();
        recipe.swipRecipe();
        time(delayTimeMs);

        //切换到微波炉菜谱选项卡
        recipe.clickRecipeWBL();
        recipe.swipRecipe();
        time(delayTimeMs);

    }


    /**
     * 1、进入某个菜谱的内部
     * 2、点击菜谱详情
     * 3、点击开始烹饪
     * 4、验证Toast内容
     * @throws Exception
     */
    @Test
    @MethodDescription("验证菜谱内部显示")
    public void Test07_RecipeDetail() throws Exception {
        Page_recipe recipe = new Page_recipe();
        recipe.clickRecipe("奶油蛋糕卷");
        recipe.clickRecipeDetail();
        recipe.clickStartCook();
        Assert.assertTrue("未发现"+ hint +"提示信息!",  processToast(hint));
    }





    @After
    public  void TearDownMethod() throws Exception {
        //点击侧边栏登录按钮
        Page_leftbar leftbar = new Page_leftbar();
        leftbar.clickHome();
    }


    @AfterClass
    public static void TearDown() throws Exception {
        //清除菜谱缓存资源，搜索记录等等
        CmdTools.execAdbCmd("pm clear " + TAG_PACKAGEPAD,0);
    }



}
