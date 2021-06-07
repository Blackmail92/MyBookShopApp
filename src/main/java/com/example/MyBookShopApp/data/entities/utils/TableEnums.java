package com.example.MyBookShopApp.data.entities.utils;

import java.util.stream.Stream;

public class TableEnums {
    public enum BookType {
        KEPT(1), CART(2), PAID(3), ARCHIVED(4);

        private final int code;

        BookType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static BookType of(int code) {
            return Stream.of(BookType.values())
                    .filter(type -> type.getCode() == code)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

        }
    }

    public enum ContactType {
        PHONE, EMAIL;

        public static ContactType of(String name) {
            return Stream.of(ContactType.values())
                    .filter(type -> type.name().equals(name))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }


    public enum FileType {
        PDF(1), EPUB(2), FB2(3);
        private final int code;

        FileType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static FileType of(Integer id) {
            return Stream.of(FileType.values())
                    .filter(type -> type.getCode() == id)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }

    }
}

