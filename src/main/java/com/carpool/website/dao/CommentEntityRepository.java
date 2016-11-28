package com.carpool.website.dao;

import com.carpool.domin.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qi on 2016/11/26.
 */

@Repository
public interface CommentEntityRepository extends JpaRepository<CommentEntity,Integer>{

    //得到某个人参与评论的总数
     @Query("select count(c.id) from CommentEntity as c where c.sourceUser.id =:id" )
     int getSendedCommentNums(@Param("id")String id);

    //得到某个人发出的评论数
     int countIdBySourceUserId(String id);

    //得到某个人被评论的数量
     int countIdByTargetUserId(String id);

    //根据某一次出行收到的得到对应的评论
    List<CommentEntity> findByJourneyId(int id);

    //分页得到某个人发出的评论
    @Query("from CommentEntity  c where c.sourceUser.id =:id")
    Page<CommentEntity> findBySourceUserId(@Param("id") String id, Pageable pageable);


    //分页得到某个收到的评论
    @Query("from CommentEntity  c where c.targetUser.id =:id")
    Page<CommentEntity> findByTargetUserId(@Param("id")String id,Pageable pageable);

    //得到某个人的信誉积分
    @Query("select sum(c.credit) from CommentEntity c where  c.targetUser.id=:id")
     Double sumOfCreditByUserId(@Param("id") String id);

    //得到某一个用户在一次行程中对某一个用户的评价数,如果没有，返回空

    @Query("select count(c.id) from CommentEntity c where c.sourceUser.id=:sourceId" +
            " and  targetUser.id =:targetId and c.journey.id =:journeyId")
    Integer getNumsOfCommentOneToAnotherInJour(@Param("sourceId")String sourceId,
                                               @Param("targetId")String targetId,
                                               @Param("journeyId")Integer journeyId);
}
