package com.stady.cars;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CarsApplicationTests {

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
					return Math.max(findMaxDepth(tree.left(), acc + 1), findMaxDepth(tree.right(), acc + 1));
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


		var tree = new Tree(1,
				new Tree(3, new Nill(),
						new Tree(5,
								new Tree(6, new Nill(),
										new Tree(7, new Nill(),
												new Tree(8, new Nill(), new Nill()))), new Nill())
				),
				new Tree(2, new Nill(), new Nill())
		);
		assertEquals (Tree.findMaxDepth(tree, 0),6);

	}
}
