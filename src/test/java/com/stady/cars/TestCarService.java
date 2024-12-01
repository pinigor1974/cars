package com.stady.cars;

import com.stady.cars.configuration.CarApplicationConfiguration;
import com.stady.cars.domain.model.Cars;
import com.stady.cars.domain.model.Codes;
import com.stady.cars.service.SendByService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TestCarService {
    @Autowired
    private CarApplicationConfiguration carApplicationConfiguration;
    @Autowired
    private SendByService sendByService;

    @Test
    public void testSendCarCode() {
       assertEquals(sendByService.send(Codes.engineOff, carApplicationConfiguration.getCars().get(Cars.LINKOLN.name())), Codes.engineOff);
       assertEquals(sendByService.send(Codes.engineOff, carApplicationConfiguration.getCars().get(Cars.LADA.name())), Codes.error);

        Assertions.assertThrows(NumberFormatException.class, () -> {
            sendByService.send(Codes.engineOn, carApplicationConfiguration.getCars().get(Cars.LADA.name()));
        }, "NumberFormatException error was expected");
    }
    @Test
    void maxTreeDepth(){
        interface TTree {
            TTree left();
            TTree right();
            Integer vle();
            Boolean isEmpty();

        }

        class Tree implements TTree{
            public  Integer v;
            public TTree left;
            public  TTree right;

            @Override
            public Boolean isEmpty()  {
                return false;
            }

            @Override
            public TTree left() {
                return left;
            }

            @Override
            public TTree right() {
                return right;
            }

            @Override
            public Integer vle() {
                return v;
            }

            public Tree(Integer v, TTree left, TTree right){
                this.left = left;
                this.right = right;
                this.v = v;

            }
            static Integer findMaxDepth(TTree  tree, Integer acc ) {

                if(tree.isEmpty()) {
                    return acc;
                }else{
                    return Math.max(findMaxDepth(tree.left(), acc + 1), findMaxDepth(tree.left(), acc + 2));
                }

            }

        };

        final class Nill implements TTree{
            @Override
            public TTree left() {
                return null;
            }

            @Override
            public TTree right() {
                return null;
            }

            @Override
            public Integer vle() {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return getClass() == obj.getClass();
            }
            @Override
            public Boolean isEmpty()  {
                return true;
            }
        };


        var tree = new Tree(1, new Nill(), new Tree(2, new Nill(), new Nill()));
        assertEquals (Tree.findMaxDepth(tree, 0),2);

    }



}
