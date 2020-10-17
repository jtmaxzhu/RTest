package page.rokipad;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import base.BasePage;
import lib.LogUtil;
import lib.VariableDescription;

import static config.Config.TAG_PACKAGEPAD;
import static config.Config.delayTimeMs;
import static lib.ComUtil.WaitForExists;
import static lib.ComUtil.WaitForExistsList;
import static lib.ComUtil.getRGB;
import static lib.ComUtil.mDevice;
import static lib.ComUtil.time;

/**
 * Created by liuxh on 2018/8/31.
 */

public class Page_recipe extends BasePage {


    @VariableDescription("搜索输入框")
    private UiObject2 search;

    @VariableDescription("搜索按钮")
    private UiObject2 search_btn;

    @VariableDescription("搜索提示")
    private UiObject2 searchTip;


    @VariableDescription("搜索提示")
    private UiObject2 record;

    public Page_recipe() {
    }


    /**
     * 先一直往左滑动，再一直往右滑
     * @throws Exception
     */
    public void swipRecipe() throws Exception{
        WaitForExists(By.res(TAG_PACKAGEPAD, "recipe_show")).scroll(Direction.LEFT,2.5F,3000);
        time(delayTimeMs);
        WaitForExists(By.res(TAG_PACKAGEPAD, "recipe_show")).scroll(Direction.RIGHT,2.5F,3000);
    }




    /**
     * 获取菜谱搜索结果提示
     * @throws Exception
     */
    public String getSearchResult() throws Exception{
        searchTip = WaitForExists(By.res(TAG_PACKAGEPAD, "search_result"));
        return searchTip.getText();
    }


    /**
     * 设置菜谱搜索框文字
     * @throws Exception
     */
    public void setRecipe(String recipeName) throws Exception{
        search = WaitForExists(By.res(TAG_PACKAGEPAD, "search"));
        search.setText(recipeName);
        time(delayTimeMs);
    }

    /**
     * 点击菜谱搜索
     * @throws Exception
     */
    public void clickRecipeSearch() throws Exception{
        search_btn = WaitForExists(By.res(TAG_PACKAGEPAD, "search_btn"));
        search_btn.click();
        time(delayTimeMs);
    }

    /**
     * 获取菜谱搜索记录，三个对象，这里无法使用常规方式获取，使用Bounds
     * @throws Exception
     */
    public List<UiObject2> getObj() throws Exception{
        //延时500ms等待页面加载完毕
        time(delayTimeMs);
        List<UiObject2> recpObj = new ArrayList<>();
        List<UiObject2> list = WaitForExistsList(By.clazz("android.widget.TextView"));
        for (UiObject2 obj : list){
            Rect rect = obj.getVisibleBounds();
            if (rect.left == 589 && rect.right == 663 && rect.top == 25 && rect.bottom == 65){
                recpObj.add(obj);
            }else if(rect.left == 683 && rect.right == 757 && rect.top == 25 && rect.bottom == 65){
                recpObj.add(obj);
            }else if(rect.left == 777 && rect.right == 829 && rect.top == 25 && rect.bottom == 65){
                recpObj.add(obj);
            }
        }
        return recpObj;

    }

    /**
     * 点击菜谱灶具选项卡
     * @throws Exception
     */
    public void clickRecipeZJ() throws Exception{
        WaitForExists(By.text("灶具")).click();
        time(delayTimeMs);
    }

    /**
     * 点击无人锅选项卡
     * @throws Exception
     */
    public void clickRecipeWRG() throws Exception{
//        List<UiObject2> uiObject2s = WaitForExistsList(By.text("无人锅"));
//        //第二个对象才是目标控件
//        uiObject2s.get(1).click();
        WaitForExistsList(By.res(TAG_PACKAGEPAD, "item_show")).get(2).click();
        time(delayTimeMs);
    }


    /**
     * 点击菜谱电烤箱选项卡
     * @throws Exception
     */
    public void clickRecipeDKX() throws Exception{
        WaitForExists(By.text("电烤箱")).click();
        time(delayTimeMs);
    }


    /**
     * 点击菜谱电蒸箱选项卡
     * @throws Exception
     */
    public void clickRecipeDZX() throws Exception{
        WaitForExists(By.text("电蒸箱")).click();
        time(delayTimeMs);
    }

    /**
     * 点击菜谱微波炉选项卡
     * @throws Exception·
     */
    public void clickRecipeWBL() throws Exception{
        WaitForExists(By.text("微波炉")).click();
        time(delayTimeMs);
    }


    /**
     * 验证返回的菜谱名称列表是否包含目标文字
     * @return
     */
    public boolean checkRecipe(String recipeName){
        if (mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"search_result")),1000)){
            return true;
        }else {
            List<UiObject2> uiObject2s = WaitForExistsList(By.res(TAG_PACKAGEPAD, "recipe_name"));
            for (UiObject2 obj:uiObject2s){
                if (!obj.getText().contains(recipeName)){
                    return false;
                }
            }
            return true;
        }
    }


    /**
     * 验证目标菜谱是否存在
     * @return
     */
    public boolean checkRecipeIsExist(String recipeName){
        if (mDevice.wait(Until.hasObject(By.res(TAG_PACKAGEPAD,"search_result")),1000)){
            return true;
        }else {
            List<UiObject2> uiObject2s = WaitForExistsList(By.res(TAG_PACKAGEPAD, "recipe_name"));
            for (UiObject2 obj:uiObject2s){
                if (obj.getText().equals(recipeName)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 点击某个菜谱
     * @return
     */
    public void clickRecipe(String recipeName) throws InterruptedException {
        WaitForExists(By.text(recipeName)).click();
        time(delayTimeMs);
    }


    /**
     * 点击菜谱详情
     * @return
     */
    public void clickRecipeDetail() throws InterruptedException {
        WaitForExists(By.res(TAG_PACKAGEPAD, "recipe_detail")).click();
        time(delayTimeMs);
    }

    /**
     * 点击开始烹饪
     * @return
     */
    public void clickStartCook() throws InterruptedException {
        WaitForExists(By.res(TAG_PACKAGEPAD, "ll_start_cook")).click();
        time(delayTimeMs);
    }













}

