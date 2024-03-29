package com.healthykitchen.springboot.controller;

import com.healthykitchen.springboot.dao.*;
import com.healthykitchen.springboot.pojo.*;
import com.healthykitchen.springboot.pojo.Collection;
import com.healthykitchen.springboot.result.Result;
import com.healthykitchen.springboot.result.ResultFactory;
import com.healthykitchen.springboot.service.CollectService;
import com.healthykitchen.springboot.service.RecipeService;

import com.healthykitchen.springboot.service.TagService;

import com.healthykitchen.springboot.service.UserService;
import com.healthykitchen.springboot.utils.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @className:
 * @description: 发布:问题记录1.添加步骤2.添加图片 返回类型未确定
 * @author: anonym_co
 * @date: 15:57 2019/11/17
 * @version: v1.0
 */
@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeDAO recipeDAO;
    @Autowired
    private CollectService collectService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private UserService userService;
    @Autowired
    private RecipeStepDAO recipeContentDAO;
    @Autowired
    private  LikeDAO likeDAO;


    /**
     * 【主页】按时间获取所有菜谱
     * @return
     */
    //获取所有菜谱 按时间排序
    @GetMapping("api/recipelist")
    @ResponseBody
    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes=recipeDAO.getAllRecipes();
        for(Recipe i: recipes ){
            int userId=i.getRecipeUserId();
            i.setRecipeUsername(userService.getuserInfoById(userId).getUsername());
        }
        return recipes;
    }

    @PostMapping("api/steplist")
    @ResponseBody
    public List<RecipeContent> getStepInRecipe(@RequestParam int recipeId){
        Recipe recipe = recipeService.getRecipeById(recipeId);
        List<RecipeContent> rs = recipeContentDAO.getRecipeStepList(recipe);
        return rs;
    }


    /**
     * 【主页】菜谱排行榜
     * @return
     */
    //根据菜谱热爱程度获取菜谱
//    @CrossOrigin(origins = {"http://0.0.0.0:8080", "null"})
    @PostMapping("api/recipeRank")
    @ResponseBody
    public  List<Recipe> getRecipeByLike(){
        List<Recipe> recipes=this.recipeService.getRecipeByLike();
        return  recipes;
    }

    /**
     * 【搜索】根据用户名字获取菜谱
     * @param username
     * @return
     */
    @PostMapping("api/getRecipeByUsername")
    @ResponseBody
    public List<Recipe> getRecipeByUserName(@RequestParam(value = "username") String username){
        List<Recipe> recipes=recipeService.getRecipeByUserName(username);
        return recipes;
    }

    @PostMapping("api/getRecipeByUserId")
    @ResponseBody
    public List<Recipe> getRecipeByUserId(@RequestParam(value = "userId") int userId){
        List<Recipe> recipes=recipeService.getRecipeByUserId(userId);
        return  recipes;
    }


    /**
     * 【个人】获取本用户发布的菜谱
     * @param request
     * @return
     */
    @PostMapping("api/userrecipelist")
    @ResponseBody
    public List<Recipe> getRecipeByUserId(HttpServletRequest request) {
        HttpSession session=request.getSession(true) ;
        User user=(User)session.getAttribute("User");
        List<Recipe> recipes=this.recipeService.getRecipeByUserId(user.getUserId());
        return recipes;
    }

    /**
     * 【菜谱页】获取菜谱原料
     * @param recipeId
     * @return
     */
    @PostMapping("api/getRecipeMaterial")
    @ResponseBody
    public List<RecipeMaterial> getRecipeMaterial(@RequestParam(value = "recipeId")int recipeId){
        return recipeService.getRecipeMaterial(recipeId);
    }

    /**
     * 【菜谱页】获取菜谱评论
     * @param recipeId
     * @return
     */
    @PostMapping("api/getRecipeComment")
    @ResponseBody
    public List<CommentDetail> getRecipeComment(@RequestParam(value = "recipeId") int recipeId){
        List<Comment> commentList = recipeService.getRecipeComment(recipeId);
        List<CommentDetail> detailList = new ArrayList<>();
        for(Comment c:commentList) {
            CommentDetail detail = new CommentDetail();
            detail.setUserName(userService.getUserNameById(c.getCommentUserId()));
            detail.setTime(c.getCommentTime());
            detail.setPic(userService.getuserInfoById(c.getCommentUserId()).getImage());
            detail.setContent(c.getCommentContent());
            detailList.add(detail);
        }
        return detailList;
    }



    /**
     * 【菜谱页】根据菜谱ID获取菜谱
     * @param recipeId
     * @return
     */
    @PostMapping("api/getRecipebyId")
    @ResponseBody
    public Recipe getRecioeById(@RequestParam(value = "recipeId") int recipeId){
        return recipeService.getRecipeById(recipeId);
    }

    /**
     *【搜索】根据菜谱名字获取菜谱
     * @param tagName
     * @return
     */
    @PostMapping("api/searchrecipebytag")
    @ResponseBody
    public List<Recipe> getRecipeByTag(@RequestParam(value = "tagName") String tagName){
        //int tagId=tagService.getTagId(tagName);
        List<Recipe> recipes=this.recipeService.getRecipeByTag(tagName);
        return recipes;
    }

    @PostMapping("api/getRecipeByName")
    @ResponseBody
    public List<Recipe> getRecipeByName(@RequestParam("recipeName") String name){
        List<Recipe> recipes=recipeService.getRecipeByName(name);
        return recipes;
    }


    @PostMapping
    @ResponseBody
    public int getMaterialCalorie(@RequestParam(value = "materialId") int materialId){
        return 0;
    }

    /**
     * 【菜谱页】点赞菜谱
     * @param rId
     * @return
     */
    @PostMapping("api/like")
    @ResponseBody
    public Result likeRecipe(@RequestParam(value = "recipeId") int rId,@RequestParam(value = "userId")int userId) {
        try {
            User user = userService.getuserInfoById(userId);
            int uId;
            uId = userId;
            Recipe recipe = recipeService.getRecipeById(rId);
            Like like=new Like();

            like.setLikeUserId(userId);
            like.setLikeRecipeId(rId);
            DateUtil date=new DateUtil();
            like.setLikeTime(date.getTime());
            likeDAO.insertLike(like);
            System.out.println("success");
            recipe.setLikeNum(recipe.getLikeNum() + 1);
            recipeDAO.updateRecipeLike(recipe);
            return ResultFactory.buildSuccessResult(recipeService.getRecipeById(recipe.getRecipeId()));

        } catch(Exception e) {
            return ResultFactory.buildFailResult("点赞失败！");
        }

    }

    @PostMapping("api/dislike")
    @ResponseBody
    public void dislikeRecipe(@RequestParam int recipeId, @RequestParam int userId) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        collectService.deleteLike(recipeId, userId);
        recipe.setLikeNum(recipe.getLikeNum()-1);
    }


    /**
     * 【菜谱页】收藏菜谱
     * @param rId
     * @return
     */
    @PostMapping("api/collect")
    @ResponseBody
    public Result collectRecipe(@RequestParam(value = "recipeId") int rId, @RequestParam(value = "userId") int userId) {
        String cName="DEFAULT";
//        if(collectService.ifExist(userId, cName)) {
//            int recipeNums = collectionDAO.getRecipeNums(userId);
//            Collection c = new Collection();
//            c.setCollectionId(recipeNums+1);
//            c.setCollectionName(cName);
//            c.setCollectionRecipeId(rId);
//            c.setCollectionUserId(userId);
//            collectionDAO.addCollection(c);
//            return ResultFactory.buildSuccessResult(c);
//        } else {
        try{
            Collection collection= new Collection();
            collection.setCollectionName("DeFault");
            collection.setCollectionUserId(userId);
            collection.setCollectionRecipeId(rId);
            collectionDAO.addCollection(collection);
            return ResultFactory.buildSuccessResult(collection);
        }catch (Exception e){
            return ResultFactory.buildFailResult("该收藏夹不存在！");
        }
    }

    /**
     * 【个人】添加收藏夹
     * @param cName
     * @param httpSession
     * @return
     */
    @PostMapping("api/addCollection")
    @ResponseBody
    public Result createNewCollection(@RequestParam("collectionName") String cName, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("User");
        int uId = user.getUserId();
        if(!collectService.ifExist(uId, cName)) {
            int recipeNums = collectionDAO.getRecipeNums(uId);
            Collection c = new Collection();
            c.setCollectionUserId(uId);
            c.setCollectionName(cName);
            c.setCollectionId(recipeNums + 1);
            collectService.createCollection(c);
            return ResultFactory.buildSuccessResult(c);
        } else {
            return ResultFactory.buildFailResult("该收藏夹已存在！");
        }
    }


    /**
     * 【主页】发布菜谱
     */
    @PostMapping(value = "api/release")
    @ResponseBody
    public Result releaseRecipe(@RequestParam String recipeDesc, @RequestParam String recipeName, @RequestParam int size, @RequestParam String recipeTag, MultipartFile pic,
                                @RequestParam int userId,@RequestParam(value = "materialName")String[] materialName,@RequestParam("materialCount") int[] materialCount) {
        try {
            Recipe r = new Recipe();
            DateUtil date = new DateUtil();
            int uId = userId;
            r.setRecipeName(recipeName);
            r.setRecipeDesc(recipeDesc);
            r.setRecipeTag(recipeTag);
            r.setSize(size);
            r.setRecipeImage(upload(pic));
            r.setRecipeTime(date.getTime());
            r.setRecipeUserId(uId);
            recipeService.addRecipe(r);

            for(int i=0;i<materialName.length;i++) {
                RecipeMaterial rm = new RecipeMaterial();
                rm.setRecipeId(r.getRecipeId());
                rm.setMaterialName(materialName[i]);
                rm.setMaterialCount(materialCount[i]);
                recipeService.addRecipeMaterial(rm);
            }
            return ResultFactory.buildSuccessResult(r);
        } catch (Exception e) {
            return ResultFactory.buildFailResult("添加菜谱失败！");
        }
    }

    @PostMapping("api/mupload")
    @ResponseBody
    public void uploadmore(@RequestParam String[] stepDesc, @RequestParam MultipartFile picList)
    {
        int recipeId = recipeDAO.getRecipeNum();
        System.out.println(stepDesc.length);
        try{
            for(int i=0;i<stepDesc.length;i++) {
                RecipeContent rc = new RecipeContent();
                rc.setStepDesc(stepDesc[i]);
                rc.setImage(upload(picList));
                rc.setRecipeId(recipeId);
                rc.setStepId(i+1);
                recipeContentDAO.addRecipeStep(rc.getStepId(),rc.getRecipeId(),rc.getStepDesc(),rc.getImage());
            }
        }
        catch (Exception e) {
            System.out.println("错了！");
        }
    }

//    public void addStepToRecipe(List<MultipartFile> picList, List<RecipeContent> recipeContentList, int recipeId) {
//        int i = 0;
//        for(RecipeContent r: recipeContentList) {
//            RecipeContent rs = r;
//            rs.setImage(upload(picList.get(i)));
//            rs.setStepId(recipeService.countRecipeStep(recipeId) + 1);
//            rs.setRecipeId(recipeId);
//            recipeService.addStep(rs);
//            i++;
//        }
//    }


//    @GetMapping("api/test")
//    @ResponseBody
//    public Material getMaterial(String materialName){
//        return materialDao.getMaterialCalorie(materialName);
//    }


    /**
     * 【菜谱页】向菜谱中添加食材
     * @return
     */
    @PostMapping("api/addRecipeMaterial")
    @ResponseBody
    public Result addReciepeMaterial(@RequestParam(value = "recipeId")int recipeId,@RequestParam(value = "materialName[]")List<String> materialName,@RequestParam("materialCount[]") int[] materialCount){
        try{
//            List<RecipeMaterial> recipeMaterials1=new ArrayList<>();
//            RecipeMaterial temp=new RecipeMaterial();
//            int recipeId=recipeDAO.getRecipeNum();
//            temp.setRecipeId(recipeId+1);
//            temp.setMaterialName(materialName);
//            temp.setMaterialCount(materialCount);
//            recipeMaterials1.add(temp);
            for(int i=0;i<materialName.size();i++) {
                RecipeMaterial rm = new RecipeMaterial();
                rm.setRecipeId(recipeId);
                rm.setMaterialName(materialName.get(i));
                rm.setMaterialCount(materialCount[i]);
                recipeService.addRecipeMaterial(rm);
            }
            return ResultFactory.buildSuccessResult("success");
        } catch (Exception e)
        {
            return ResultFactory.buildFailResult("添加食材失败！");
        }
    }

    /**
     * 【菜谱页】获取菜谱总卡路里
     * @param recipeId
     * @return
     */
    @PostMapping("api/getRecipeCalorie")
    @ResponseBody
    public int getRecipeCalorie(int recipeId){
        Recipe recipe=recipeService.getRecipeById(recipeId);
        return recipeService.getRecipeCalorie(recipe);
    }

    /**
     * 【菜谱页】根据材料名字获取卡路里
     * @param materialName
     * @return
     */
    @PostMapping("api/getMaterialCalorie")
    @ResponseBody
    public int getMaterialCalorie(@RequestParam(value = "materialName") String materialName){
        return materialDao.getMaterialCalorie(materialName).getCalorie();
    }



    /**
     * 添加菜谱评论
     * @param rId
     * @param content
     * @return
     */
    @PostMapping("api/comment")
    @ResponseBody
    public Result commentToRecipe(@RequestParam("recipeId") int rId, @RequestParam("content") String content, @RequestParam int userId) {
        DateUtil time = new DateUtil();
        User user = userService.getuserInfoById(userId);
        CommentDetail detail = new CommentDetail();
        Comment comment = new Comment();
        comment.setCommentRecipeId(rId);
        comment.setCommentContent(content);
        comment.setCommentUserId(userId);
        comment.setCommentTime(time.getTime());
        detail.setContent(content);
        detail.setPic(user.getImage());
        detail.setTime(time.getTime());/**/
        detail.setUserName(user.getUsername());
        recipeService.addComment(comment);
        return ResultFactory.buildSuccessResult(detail);
    }

//    public String upload(MultipartFile pic){
//        if (pic.isEmpty()) {
//            System.err.println("上传文件不可为空");
//        }
//        String fileName = pic.getOriginalFilename();//得到文件名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));//得到后缀名
//        System.err.println("suffixName:" + suffixName);
//        String filepath = "/Users/anonym_co/Desktop/";//指定图片上传到哪个文件夹的路径
//        fileName = UUID.randomUUID() + suffixName;//重新命名图片，变成随机的名字
//        System.err.println("fileName:" + fileName);
//        File dest = new File(filepath + fileName);//在上传的文件夹处创建文件
//        try {
//            pic.transferTo(dest);//把上传的图片写入磁盘中
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return filepath+fileName;
//
//    }

    public String upload(MultipartFile file) {
        String folder = "/Users/anonym_co/Desktop/1";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        String filename = file.getName();
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
//            System.out.println(file.getOriginalFilename());
//            System.out.println("http://localhost:8443/api/file/" + f.getName());
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
