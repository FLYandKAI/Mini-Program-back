package com.doke.massage.massage.controller;

import com.alibaba.fastjson.JSONObject;
import com.doke.massage.massage.pojo.Discuss.Comment;
import com.doke.massage.massage.pojo.Discuss.Favorite;
import com.doke.massage.massage.pojo.Discuss.Invitation;
import com.doke.massage.massage.pojo.Discuss.InvitationAndUser;
import com.doke.massage.massage.pojo.V.V_comment;
import com.doke.massage.massage.pojo.V.V_like_user;
import com.doke.massage.massage.service.discussService;
import com.doke.massage.massage.service.likeStarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@ResponseBody
public class discussController {
    @Autowired
    private discussService discussService;
    @Autowired
    private likeStarService likeStarService;
    @ApiOperation(value = "删除收藏")
    @PostMapping("/deleteFavorite")
    public Integer deleteFavorite(String id,String token){
        return discussService.deleteFavorite(id,token);
    }
    @ApiOperation(value = "删除帖子")
    @PostMapping("/deleteInvitation")
    public Integer deleteInvitation(String token,String Invitation_id){
        return discussService.deleteInvitation(token,Invitation_id);
    }

    @ApiOperation(value = "我的动态-获取贴子列表")
    @GetMapping("/getMyInvitation")
    public Object getMyInvitationList(String token){
        return discussService.getMyInvitationList(token);
    }

    @ApiOperation(value = "我的点赞-获取点赞的用户表")
    @GetMapping("/getMyLikeList")
    public Object getMyLikedList(String token){
        return likeStarService.getMyLikedList(token);
    }
    @ApiOperation(value = "获取点赞的用户表")
    @GetMapping("/getLiked")
    public List<V_like_user> getLiked(String invitation_id){
        return likeStarService.getLiked(invitation_id);
    }
    @ApiOperation("新增点赞/取消点赞")
    @GetMapping("/likeStar")
    public String likeStar(String token,String invitation_id){
        return likeStarService.saveLiked(token,invitation_id);
    }


    @ApiOperation(value = "根据用户的id获取收藏列表")
    @GetMapping("/getFavorite")
    public Object getFavorite(String token){
        return discussService.getFavorite(token);
    }
    @ApiOperation(value = "新增收藏")
    @PostMapping("/saveFavorite")
    public Integer saveFavorite(Favorite favorite,String token){
       return discussService.saveFavorite(favorite,token);
    }


    @ApiOperation(value = "获取评论列表")
    @GetMapping("/getComment")
    public List<V_comment> getComment(@ApiParam("传入贴子的id") String invitation_id){
        return discussService.getComment(Integer.valueOf(invitation_id));
    }
    @ApiOperation(value = "新增评论")
    @PostMapping("/saveComment")
    public String saveComment(Comment comment,String token){
        return discussService.saveComment(comment,token);
    }


    @ApiOperation(value = "获取贴子列表，带分页")
    @GetMapping("/getInvitation")
    public List<InvitationAndUser> getInvitation(Integer page){
        return discussService.findInvitation(page);
    }
    @PostMapping("/saveInvitation")
    public Integer saveInvitation(Invitation invitation,
                                  String token){
        System.out.println(token+"contro");
        return discussService.saveInvitation(invitation,token);
    }
    @ApiOperation(value = "存储图片")
    @PostMapping("/upLoadFile")
    public String upLoadFile(@Param("file") MultipartFile file,
                             @Param("i") Integer i,
                             @Param("id") String id
                            ) throws IOException {
        String fileName = file.getOriginalFilename();
        if(i==0){
            String s = discussService.upLoadFile(file.getBytes(), fileName);
            discussService.updataInvitation(s,Integer.parseInt(id));
            discussService.saveImage("https://www.mofashiteam.com/"+s,Integer.parseInt(id));
            return s;
        }
        String s = discussService.upLoadFile(file.getBytes(),fileName);
        discussService.saveImage("https://www.mofashiteam.com/"+s,Integer.parseInt(id));
        return s;
    }
    @ApiOperation(value = "详情页获取多图")
    @GetMapping("/getImageForInvitation")
    public List<String> getImageForInvitation(Integer id){
        return discussService.getImageForInvitation(id);
    }
}
