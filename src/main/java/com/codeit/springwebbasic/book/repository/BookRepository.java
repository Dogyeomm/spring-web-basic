package com.codeit.springwebbasic.book.repository;

import com.codeit.springwebbasic.book.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class BookRepository {
    // 웹 애플리케이션은 동시에 여러 요청이 한꺼번에 들어올 수 있기 때문에
    // 멀티 스레드에서도 안전하게 사용할 수 있는 HashMap을 사용.
    // AtomicLong도 마찬가지로 Long 타입의 정수를 멀리 스레드에서 안전하게 공유 가능.
    private final Map<Long, Book> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    // 도서 저장
    public void Save(Book book) {
       if (book.getId() == null){
           book.setId(sequence.getAndIncrement());
       }
       store.put(book.getId(),book);
       return book;
    }

    // 도서 조회
    public List<Book> findAll() {
        // Map에서 Value들만 전부 꺼낸 후 List로 반환.
        return new ArrayList<>(store.values());
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
