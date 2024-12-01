package com.stady.cars;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.stream.Stream;

@SpringBootTest
class CarsApplicationTests {

	@Test
	void contextLoads() {
		String st = "МммоооОссСсккввВаАа  сслллЕЕЕззЗзамм нНее вввееееритТ!!";
		List<String> worlds = List.of( st.toLowerCase().split(" "));
		var listWords = worlds.stream()
				.map(
						v -> removeDublicate(new StringCharacterIterator(v), '-', new LinkedList<>())).filter(v-> !v.isEmpty()).map(v->
						{
							v.set(0,Character.toUpperCase(v.getFirst()));
							return Arrays.toString(v.toArray(new Character[0]));
						}).reduce((v1,v2)-> (v1 + " " + v2));

		int aaa = 1;

	}

	List<Character>  removeDublicate(StringCharacterIterator it, Character prevValue, List<Character> acc) {
		if (it.current() != CharacterIterator.DONE){
			var curCharacter = it.current();

			if(curCharacter == prevValue){
				it.next();
				return removeDublicate(it,  curCharacter,  acc);
			}else{
				acc.addLast(curCharacter);
				it.next();
				return removeDublicate(it,  curCharacter,  acc);
			}
		} else{
			return acc;
		}
	}


}
