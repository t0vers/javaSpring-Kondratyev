package org.example.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Message {
    HELLO("""
            Приложение для отслеживания контактов пользователей.
            Список команд можно посмотреть командой: help"""),
    HELP("""
            Приложение для отслеживания контактов пользователей.
            Команды:
            1. Добавить пользователя - add
            2. Удалить пользователя - delete
            3. Просмотреть список всех контактов - get-all
            Примеры использования:
            1. add Иванов Иван Иванович; +890999999; someEmail@example.example
            (Добавляет пользователя у которого
                ФИО: Иванов Иван Иванович,
                Телефон: +890999999,
                email: someEmail@example.example)
            2. delete someEmail@example.example
            (Удаляет пользователя с почтой someEmail@example.example)
            3. get-all
            """),
    ACCESS_ADD("Пользователь успешно добавлен"),
    ACCESS_DELETE("Пользователь успешно удален"),
    NULL_CONTACTS("Список пользователей пуст"),
    NOT_FOUND_USER("Пользователь не найден");

    private final String text;


}
