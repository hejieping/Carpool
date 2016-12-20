package com.carpool.website.service;

import com.carpool.configuration.GlobalConstants;
import com.carpool.domain.RoomEntity;
import com.carpool.domain.RoomState;
import com.carpool.domain.UserEntity;
import com.carpool.exception.ResourceNotFoundException;
import com.carpool.exception.RoomNullException;
import com.carpool.exception.UserNullException;
import com.carpool.website.dao.RoomEntityRepository;
import com.carpool.website.dao.UserEntityRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * Project: Carpool
 * Package: com.carpool.website.service
 * Author:  Novemser
 * 2016/11/29
 */

@Service
public class RoomService {

    @Autowired
    private RoomEntityRepository roomEntityRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Transactional
    public void createRoom(String roomname,
                           String startPoint,
                           String endPoint,
                           int numberLimit,
                           Date startTime,
                           String hostId) throws Exception {

        UserEntity userEntity = userEntityRepository.findOne(hostId);
        if (null == userEntity)
            throw new UserNullException("createRoom", "用户不存在！");

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomname(roomname);
        roomEntity.setEndPoint(endPoint);
        roomEntity.setNumberLimit(numberLimit);
        // 目前只有房主一个人
        roomEntity.setCurrentNums(1);
        roomEntity.setStartPoint(startPoint);
        roomEntity.setStartTime(startTime);
        roomEntity.setState(RoomState.UNLOCKED);

        // 获取系统当前时间戳
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        roomEntity.setCreateTime(timestamp);

        roomEntity.setHost(userEntity);
        // 把房主加到房间里
        // 因为是UserEntity维护多对多表
        // 所以只用修改User里的多方表就可以了
        userEntity.getUserParticipateRooms().add(roomEntity);

        RoomEntity entity = roomEntityRepository.saveAndFlush(roomEntity);
        System.out.println(entity.getId());
    }

    public Page<RoomEntity> listUserRooms(String userId) {
        UserEntity userEntity = userEntityRepository.findOne(userId);
        Pageable p = new PageRequest(0, 10);
        return roomEntityRepository.findUserRooms(userEntity, p);
    }

    public Integer getRoomPageCount() {
        return (int) Math.ceil(roomEntityRepository.getRoomCount() * 1.0 / GlobalConstants.HOME_CARPOOL_PAGE_SIZE);
    }

    public Integer getRoomsCount() {
        return roomEntityRepository.getRoomCount();
    }

    public RoomEntity findById(int id) {
        RoomEntity entity = roomEntityRepository.findOne(id);

        if (null == entity)
            throw new ResourceNotFoundException();

        return entity;
    }

    @Transactional
    public void addUserToRoom(int roomId, String userId) throws Exception {
        UserEntity user = userEntityRepository.findOne(userId);
        if (null == user)
            throw new UserNullException("addUserToRoom", "用户不存在！");

        RoomEntity room = roomEntityRepository.findOne(roomId);
        if (null == room)
            throw new RoomNullException("addUserToRoom", "房间不存在！");

        user.getUserParticipateRooms().add(room);
    }

    /***
     * 模糊搜索房间
     *
     * @param startPoint 起点，模糊搜索
     * @param endPoint 终点，模糊搜索
     * @param from 开始时间
     * @param offset 以开始时间为基准，显示往后多少天的结果
     * @return 找到的结果
     * @throws ParseException 解析错误
     */
    @SuppressWarnings("deprecation")
    public Page<RoomEntity> listRoomsInDays(String startPoint, String endPoint,
                                            Date from, int offset, int page, int size) throws ParseException {
        // TODO:
        // 使用LocationUtil去检查point是否合法
        // 会调用百度API
        Pageable p = new PageRequest(page, size);
        from = new Date(from.getYear(), from.getMonth(), from.getDate());
        Date to = DateUtils.addDays(from, offset);
        return roomEntityRepository.findRoomStartEndPointLikeInDays(startPoint,
                endPoint,
                from, to, p);
    }



    public Page<RoomEntity> findRoom(Pageable pageable) {
        return roomEntityRepository.findAll(pageable);
    }

    public Page<RoomEntity> findRoom(int page, int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return roomEntityRepository.findAll(pageRequest);
    }

    public void lockRoomById(int roomId) throws Exception {
        changeRoomState(roomId, RoomState.LOCKED);
    }

    public void unLockRoomById(int roomId) throws Exception {
        changeRoomState(roomId, RoomState.UNLOCKED);
    }

    public void startRoomById(int roomId) throws Exception {
        changeRoomState(roomId, RoomState.STARTED);
    }

    public void endRoomById(int roomId) throws Exception {
        changeRoomState(roomId, RoomState.END);
    }

    private void changeRoomState(int roomId, RoomState locked) throws Exception {
        RoomEntity entity = roomEntityRepository.findOne(roomId);
        if (null == entity) {
            // TODO:
            // Log for error
            throw new RoomNullException("changeRoomState", "房间不存在！");
        }
        roomEntityRepository.updateState(locked, roomId);
    }
}
