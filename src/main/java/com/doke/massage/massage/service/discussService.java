package com.doke.massage.massage.service;

import com.alibaba.fastjson.JSONObject;
import com.doke.massage.massage.constant.Constant;
import com.doke.massage.massage.dao.Discuss.InvitationDao;
import com.doke.massage.massage.dao.Discuss.commentDao;
import com.doke.massage.massage.dao.Discuss.favoriteDao;
import com.doke.massage.massage.dao.Discuss.imageDao;
import com.doke.massage.massage.pojo.Discuss.*;
import com.doke.massage.massage.pojo.V.V_comment;
import com.doke.massage.massage.util.getFileName;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class discussService {
    @Autowired
    private InvitationDao invitationDao;
    @Autowired
    private imageDao imageDao;
    @Autowired
    private commentDao commentDao;
    @Autowired
    private favoriteDao favoriteDao;
    @Autowired
    private UserService userService;
    @Autowired
    private likeStarService likeStarService;
    getFileName getFileName = new getFileName();

    //删除收藏
    public Integer deleteFavorite(String id,String token){
        String s = userService.checkToken(token);
        if (s == "") return -1;
        favoriteDao.deleteByInvitation_idAndUser_id(Integer.valueOf(id),Integer.valueOf(s));
        return 200;
    }
    //删除帖子
    public Integer deleteInvitation(String token,String invitation_id){
        String s = userService.checkToken(token);
        if (s == "") return -1;
        imageDao.removeByInvitation_id(Integer.valueOf(invitation_id));
        invitationDao.removeByUser_IdAndId(Integer.valueOf(s),Integer.valueOf(invitation_id));
        return 200;
    }
    //根据token获取贴子列表
    public Object getMyInvitationList(String token){
        String s = userService.checkToken(token);
        JSONObject object = new JSONObject();
        if (s == "") {
            object.put("list", -1);
            return object;
        }else {
            object.put("list", invitationDao.findByUser_Id(Integer.valueOf(s)));
            return object;
        }
    }
    /**
     * 获取收藏
     * @param token
     * @return
     */
    public Object getFavorite(String token) {
        String s = userService.checkToken(token);
        JSONObject object = new JSONObject();
        if (s == "") {
            object.put("list", -1);
            return object;
        }else {
        List<Integer> invitation_ids = favoriteDao.findByUser_id(Integer.parseInt(s));
        object.put("list", invitationDao.findById(invitation_ids));
        return object;}
    }
    /**
     * 新增收藏
     * @param favorite
     * @return
     */
    public Integer saveFavorite(Favorite favorite,String token){
        String s = userService.checkToken(token);
        if(s==null) return -1;
        List<Favorite> fa = favoriteDao.findByInvitation_idAndUser_id(favorite.getInvitation_id(), Integer.valueOf(s));
        if (fa.size()>=1)
            return 201;
        favorite.setUser_id(Integer.parseInt(s));
        favorite.setCreateTime(new Date());
        if(favoriteDao.save(favorite)!=null) return 200;
        return 100;
    }
    /**
     * 获取评论列表
     * @param id
     * @return
     */
    public List<V_comment> getComment(Integer id){
        List<V_comment> V_comments = commentDao.findByInvitation_id(id);
        for (V_comment comment:V_comments) {
            comment.setReturnTime(new SimpleDateFormat("yyy-MM-dd").format(comment.getCreateTime()));
        }
        return V_comments;
    }
    /**
     * 新增评论
     * @param comment
     * @return
     */
    public String saveComment(Comment comment,String token){
        System.out.println(token);
        String s = userService.checkToken(token);
        if (s=="")
            return "-1";
        System.out.println(s);
        comment.setUser_id(Integer.valueOf(s));
        comment.setCreateTime(new Date());
        if(commentDao.save(comment)!=null) return "200";
        return "100";
    }
    /**
     * 获取帖子列表
     * @return
     */
    public List<InvitationAndUser> findInvitation(Integer pageNum){
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(pageNum, 20,sort);
        List<InvitationAndUser> invitations = invitationDao.find(pageable);
        for (InvitationAndUser i:invitations) {
            System.out.println(i.getId().toString());
            i.setLikeCount(likeStarService.getLikedCountByInvitationId(i.getId().toString()));
        }
        return invitations;
    }

    /**
     * 新增帖子Service
     * 成功 ：return 200
     * 失败 ：return 100
     * @param invitation
     * @return
     */
    public Integer saveInvitation(Invitation invitation,String token){
        String s = userService.checkToken(token);
        System.out.println(s);
        if(s==""){
            return -1;
        }
        invitation.setCreateTime(new Date());
        invitation.setReturnTime(new SimpleDateFormat("yyy-MM-dd").format(invitation.getCreateTime()));
        Invitation save = invitationDao.save(invitation);
        if (save!=null){
            invitationDao.setUserId(save.getId(),Integer.parseInt(s));
            return save.getId();
        }
        return 100;
    }
    public String upLoadFile(byte[] file,String fileName){
        String targetFileName = Constant.imageBasePath+"/"+getFileName.getFileName()+fileName;
        try {
            FileOutputStream out = new FileOutputStream(targetFileName);
            out.write(file);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File targetFile = new File(targetFileName);
        if(targetFile.exists())
            return targetFileName;
        return "100";
    }
    //在invitation中添加图片地址
    public int updataInvitation(String imageUrl,Integer id){
        return invitationDao.setImageUrl("https://www.mofashiteam.com/"+imageUrl,id);
    }
    //添加图片
    public Image saveImage(String imageUrl,Integer id){
        Image image = new Image();
        image.setImageUrl(imageUrl);
        Image save = imageDao.save(image);
        if(save!=null)
        {
            imageDao.setInvitationId(id,save.getId());
        }
        return save;
    }
    //根据贴子id取出图片数组
    public List<String> getImageForInvitation(Integer id){
        return imageDao.getByInvitation_Id(id);
    }
}
