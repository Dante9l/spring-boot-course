package top.zby.DTO;

public record BookDTO(Long id, String title, String author, double price) {
    public BookDTO{
        if (price < 0) {
            throw new IllegalArgumentException("price can not be negative");
        }
    }
}
