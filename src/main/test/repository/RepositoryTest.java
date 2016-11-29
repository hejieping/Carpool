package repository;


import com.carpool.website.dao.CommentEntityRepository;
import com.carpool.website.dao.JourneyEntityRepository;
import com.carpool.website.dao.UserEntityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qi on 2016/11/26.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
public class CommentRepositoryTest {


    @Autowired
    private JourneyEntityRepository journeyEntityRepository;

    @Autowired
    private CommentEntityRepository commentEntityRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Test
    public void testSaveComment()
    {
        UserEntity sourceUser  = userEntityRepository.findOne("1452778");
        JourneyEntity journeyEntity =  journeyEntityRepository.findOne(2);
        UserEntity targetEntity = userEntityRepository.findOne("1452779");
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCredit(5);
        commentEntity.setJourney(journeyEntity);
        commentEntity.setSourceUser(sourceUser);
        commentEntity.setTargetUser(targetEntity);
        commentEntityRepository.save(commentEntity);
        Assert.assertNotNull(commentEntityRepository.findOne(1));
    }

    @Test
    public void testCreaditsQuery()
    {
        Assert.assertEquals(0,commentEntityRepository.countIdBySourceUserId("1452779"));

        Assert.assertEquals(2,commentEntityRepository.countIdByTargetUserId("1452779"));

        Assert.assertEquals(new Double(10),commentEntityRepository.sumOfCreditByUserId("1452779"));

        Assert.assertTrue(commentEntityRepository.sumOfCreditByUserId("1452778")==null);
    }


    @Test
    public void testPageComment()
    {
        Sort sort = new Sort(Sort.Direction.DESC, "commentTime");
        int page = 0; int size = 1;
        Pageable pageable = new PageRequest(page, size, sort);
        Page<CommentEntity>commentEntities = commentEntityRepository.findBySourceUserId("1452778",pageable);
        Assert.assertEquals(2,commentEntities.getTotalPages());

        commentEntities = commentEntityRepository.findByTargetUserId("1452779",pageable);
        Assert.assertEquals(2,commentEntities.getTotalPages());
    }

    @Test
    public void testCommentsByJouery()
    {
        List<CommentEntity>commentEntities
                = commentEntityRepository.findByJourneyId(2);
        Assert.assertEquals(2,commentEntities.size());
    }

    @Test
    public  void  testCountNumsOfCommentToOne()
    {
        int nums = commentEntityRepository.getNumsOfCommentOneToAnotherInJour("" +
                "1452778","1452779",2);
        Assert.assertEquals(2,nums);
    }


}
