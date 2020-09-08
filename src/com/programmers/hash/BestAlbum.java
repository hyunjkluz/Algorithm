/**
 * 
 */
package com.programmers.hash;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Aug 7, 2020
 * @주요 개념 : compareTo, 우선순위
 */

class Song implements Comparable<Song> {
	public Integer idx, plays;
	public String genre;

	public Song(Integer idx, Integer plays, String genre) {
		this.idx = idx;
		this.plays = plays;
		this.genre = genre;
	}

	@Override
	public int compareTo(Song o) {
		// TODO Auto-generated method stub
		if (this.plays == o.plays) {
			return this.idx < this.idx ? -1 : 1;
		}
		return this.plays > o.plays ? -1 : 1;
	}
}

public class BestAlbum {

	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };

		System.out.println(solution(genres, plays).toString());
	}

	public static int[] solution(String[] genres, int[] plays) {
		int[] answer = {};
		Map<String, Integer> genreRankHash = new LinkedHashMap<String, Integer>();

		for (int i = 0; i < plays.length; i++) {
			genreRankHash.put(genres[i], genreRankHash.getOrDefault(genres[i], 0) + plays[i]);
		}

		List<String> keySetList = new ArrayList<>(genreRankHash.keySet());
		Collections.sort(keySetList, (o1, o2) -> (genreRankHash.get(o1).compareTo(genreRankHash.get(o2))));

		// 각 노래의 1, 2위
		HashMap<String, PriorityQueue<Song>> songRank = new HashMap<String, PriorityQueue<Song>>();
		for (int i = 0; i < genres.length; i++) {
			if (!songRank.containsKey(genres[i])) {
				songRank.put(genres[i], new PriorityQueue<Song>());
			}
			songRank.get(genres[i]).add(new Song(i, plays[i], genres[i]));
		}

		return answer;
	}

}
