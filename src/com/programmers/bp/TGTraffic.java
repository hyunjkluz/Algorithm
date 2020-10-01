/**
 * 
 */
package com.programmers.bp;

import java.text.ParseException;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 1, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/17676
 */
class Log implements Comparable<Log> {
	public int start, end;
	public int duration;

	public Log(String end, String duration) throws ParseException {
		this.duration = (int) Double.parseDouble(duration.substring(0, duration.length() - 1)) * 1000;

		String[] times = end.split(":");
		this.end += Integer.parseInt(times[0]) * 60 * 60 * 1000;
		this.end += Integer.parseInt(times[1]) * 60 * 1000;
		this.end += (int) (Double.parseDouble(times[2]) * 1000);
		this.start = this.end - this.duration + 1;
	}

	@Override
	public String toString() {
		return "Log [start=" + start + ", end=" + end + ", duration=" + duration + "]";
	}

	@Override
	public int compareTo(Log o) {
		if (this.start > o.start) {
			return 1;
		}
		return -1;
	}

}

public class TGTraffic {

	public static void main(String[] args) throws ParseException {
		String[] lines = { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" };

		System.out.println(solution(lines));

	}

	public static int solution(String[] lines) throws ParseException {
		List<Log> logs = new ArrayList<Log>();

		for (int i = 0; i < lines.length; i++) {
			String[] detail = lines[i].split(" ");
			logs.add(new Log(detail[1], detail[2]));
		}

		Collections.sort(logs);

		int max = -1;
		for (Log log : logs) {
			int[] cnt = new int[2];

			for (Log ls : logs) {
				if (log.start <= ls.end && log.start + 999 >= ls.start) {
					cnt[0] += 1;
				}

				if (log.end <= ls.end && log.end + 999 >= ls.start) {
					cnt[1] += 1;
				}
			}
			max = Math.max(max, Math.max(cnt[0], cnt[1]));
		}
		
		return max;
	}
}
