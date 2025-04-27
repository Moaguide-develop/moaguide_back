package com.moaguide.refactor.product.service;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import com.moaguide.refactor.product.repository.BookmarkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {

	@Mock
	private BookmarkRepository bookmarkRepository;

	@InjectMocks
	private BookmarkService bookmarkService;

	@Test
	void countByUser() {
		//given
		given(bookmarkRepository.countByUser("준호")).willReturn(10);

		//when
		int count = bookmarkService.countByUser("준호");

		//then
		assertThat(count).isEqualTo(10);
	}

	@Test
	void postBookmark() {
	}

	@Test
	void deleteBookmark() {
	}

	@Test
	void getProductBybookmark() {
	}

	@Test
	void getBookmarkRepository() {
	}
}