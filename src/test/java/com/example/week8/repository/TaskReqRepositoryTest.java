package com.example.week8.repository;

import com.example.week8.entity.Ad;
import com.example.week8.entity.Adv;
import com.example.week8.entity.TaskRequest;
import com.example.week8.enums.AccountRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DataJpaTest
//@EnableTransactionManagement
//@SpringBootTest
public class TaskReqRepositoryTest {

    @Autowired
    TaskRequestRepository taskRequestRepository;

//    @Autowired
//    TestEntityManager entityManager;

//    @BeforeEach
//    public void beforeEach(){ //테스트 실행할땨마다 수행시킴
//        taskRequestRepository = new MemoryMemberRepository();
//    }

    /**
     * save
     **/
    @Test
    @DisplayName("성공_저장 테스트")
    public void save_Success() {
        //given
        String taskName = "남신욱";
        TaskRequest taskRequest = TaskRequest.builder()
                .taskName(taskName)
                .requestDate("20230418")
                .taskStatus("REQ")
                .build();

        //when
        TaskRequest result = taskRequestRepository.save(taskRequest);
//        taskRequestRepository.flush();

//        entityManager.flush();

        System.out.println(taskRequestRepository.findAll().size());

        //then
//        Optional<TaskRequest> findTaskRequest = taskRequestRepository.findById(result.getId());
//        assertEquals(result.getId(), findTaskRequest.get().getId());
//        assertEquals(taskName, findTaskRequest.get().getTaskName());
//
//        System.out.println(taskRequestRepository.findAll().size());

    }

    @ParameterizedTest
    @MethodSource("temp")
    void isOdd_ShouldReturnTrueForOddNumbers(Ad ad) {
//        System.out.println(ad.get);
    }

    static Stream<Arguments> temmp() throws Throwable {
        return Stream.of(
                Arguments.of(Adv.builder()
                        .name("adv")
                        .password("1")
                        .role(AccountRoleEnum.valueOf("ROLE_ADV"))
                        .adIngActYn(1)
                        .balance(1000L)
                        .eventMoneyBalance(0L)
                        .dayLimitBudget(0L)
                        .build())
        );
    }

    /**
     * findByTaskStatus
     **/
    @Test
    @ParameterizedTest
    @ValueSource(strings = {"REQ,ING"})
    @DisplayName("TaskStatus")
    public void findByTaskStatus(String status) {

        //given
        TaskRequest taskRequest1 = TaskRequest.builder()
                .taskName("남신욱1")
                .taskStatus("REQ")
                .requestDate("20230417")
                .build();
        TaskRequest taskRequest2 = TaskRequest.builder()
                .taskName("남신욱2")
                .taskStatus("REQ")
                .requestDate("20230418")
                .build();
        taskRequestRepository.save(taskRequest1);
        taskRequestRepository.save(taskRequest2);

        //when
        List<TaskRequest> taskRequests = taskRequestRepository.findByTaskStatus(status);

        //then
        assertEquals(2,taskRequests.size());
    }

    @Test
    @DisplayName("실패_TaskStatus_REQ_일떄")
    public void findByTaskStatus_Failed() {

        //given
        TaskRequest taskRequest1 = TaskRequest.builder()
                .taskName("남신욱1")
                .taskStatus("ING")
                .requestDate("20230417")
                .build();
        TaskRequest taskRequest2 = TaskRequest.builder()
                .taskName("남신욱2")
                .taskStatus("ING")
                .requestDate("20230418")
                .build();
        taskRequestRepository.save(taskRequest1);
        taskRequestRepository.save(taskRequest2);

        //when
        List<TaskRequest> taskRequests = taskRequestRepository.findByTaskStatus("REQ");

        //then
        assertEquals(0,taskRequests.size());
    }

    /**
     * findByTaskName
     **/
    //파라미터 라이즈드 테스트
    @Test
    @ParameterizedTest //이거 써보기
    @DisplayName("성공_TaskName") //이름 간단히
    public void findByTaskName_Success(){

        //given
        TaskRequest taskRequest1 = TaskRequest.builder()
                .taskName("남신욱1")
                .taskStatus("ING")
                .requestDate("20230417")
                .build();
        taskRequestRepository.save(taskRequest1);

        //when
        TaskRequest entity = taskRequestRepository.findByTaskName("남신욱1");

        //then
        assertEquals("남신욱1",entity.getTaskName());

    }
    @Test
    @DisplayName("실패_TaskName_다른 것 호출_null")
    public void findByTaskName_Failed(){

        //given
        TaskRequest taskRequest1 = TaskRequest.builder()
                .taskName("남신욱1")
                .taskStatus("ING")
                .requestDate("20230417")
                .build();
        taskRequestRepository.save(taskRequest1);

        //when
        TaskRequest entity =taskRequestRepository.findByTaskName("남신욱2");

        //then
        Exception e = assertThrows(Exception.class,() ->
                entity.getTaskName()
        );
        System.out.println(e.getMessage());
    }

    /**
    * findByTaskPath
    **/
    @Test
    @DisplayName("성공_TaskName")
    public void findByTaskPath_Success(){

        //given
        String taskPath = "avcd";
        TaskRequest taskRequest1 = TaskRequest.builder()
                .taskName("남신욱1")
                .taskStatus("REQ")
                .taskPath(taskPath)
                .requestDate("20230417")
                .build();
        taskRequestRepository.save(taskRequest1);

        //when
        TaskRequest entity = taskRequestRepository.findByTaskPath(taskPath);

        //then
        assertEquals(taskPath,entity.getTaskPath());

    }
    @Test
    @DisplayName("실패_TaskName_다른 것 호출_null")
    public void findByTaskPath_Failed(){

        //given
        String taskPath = "avcd";
        TaskRequest taskRequest1 = TaskRequest.builder()
                .taskName("남신욱1")
                .taskStatus("ING")
                .requestDate("20230417")
                .build();
        taskRequestRepository.save(taskRequest1);

        //when
        TaskRequest entity =taskRequestRepository.findByTaskPath("남신욱2");

        //then
        Exception e = assertThrows(Exception.class,() ->
                entity.getTaskName()
        );
        System.out.println(e.getMessage());
    }

}
