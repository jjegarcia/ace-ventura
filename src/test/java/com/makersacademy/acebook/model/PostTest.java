package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostTest {
    private LocalDateTime testDate = LocalDateTime.now();
	private Post post = new Post("hello", testDate);

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}
	@Test
	public void postHasDate() { assertThat(post.getDate(), instanceOf(LocalDateTime.class)); }
//post.addTime(LocalDateTime.now());
}
