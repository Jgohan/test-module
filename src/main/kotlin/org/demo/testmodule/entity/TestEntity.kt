package org.demo.testmodule.entity

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 * Исходное описание сущности

    @Entity
    data class TestEntity(

        @Id
        val id: UUID? = null,
        val documentId: UUID? = null,
        val documentDate: String? = null,
        val dictionaryValueId: UUID? = null,
        val dictionaryValueName: UUID? = null,
        val sortOrder: String? = null
    ) {
        var testId: UUID? = null
        var testName: String = "Test"
    }

 */

/**
 * Исходная сущность представляет собой смесь самостоятельных сущностей. Для решение этой проблемы
 * была применена декомпозиция. В результате исходная сущность разделена на три сущности:
 * [DocumentEntity], [DictionaryValueEntity] и [TestEntity].
 *
 * Также исходная сущность помечена ключевым словом data, использование которого приводит к
 * генерации в том числе методов equals(), hashCode(), toString(), используя все поля, указанные в
 * основном конструкторе, что может оказать негативное влияние при работе с ORM системой. Подобный
 * эффект может быть также вызван использованием библиотеки Lombok
 * (https://thorben-janssen.com/lombok-hibernate-how-to-avoid-common-pitfalls/). Для обеспечения
 * безопасности стоит ограничить применение некоторых опций данной библиотеки. Для решения указанной
 * выше проблемы был создан абстрактный класс [AbstractEntity], содержащий поле [AbstractEntity.id]
 * и переопределённые методы [equals] и [hashCode]. Остальные сущности являются его наследниками и
 * не используют ключевое слово open в своём объявлении.
 *
 * Внутренняя структура исходной сущности была подвергнута следующим изменениями:
 *   - поля из тела класса были перемещены в конструктор по умолчанию для удобства создания объектов;
 *   - поля, семантически принадлежащие другим сущностям, вынесены в соответсвующие сущности;
 *   - поле [DocumentEntity.documentDate] имело тип [String], что не соответсвует его имени, по этой
 *     причине было решено изменить его тип на [LocalDate];
 *   - поле [DictionaryValueEntity.name] имело тип [UUID], что также противоречит его названию,
 *     новый тип - [String];
 *   - поле testId было удалено, так как по всей видимости поле [id] его дублирует;
 *   - поле [id] больше не nullable поскольку первичный ключ в таблице не может быть равен null, и
 *     внутри базы данных не создаётся;
 *   - тип связей с другими сущностями был выбран one to one, поскольку этот тип связи
 *     не противоречит исходной сущности, а предметная область не ясна для принятия решения.
 */

@Entity
@Table(name = "tests")
class TestEntity(
    override val id: UUID = UUID.randomUUID(),

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "document_id")
    val document: DocumentEntity? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "dictionary_value_id")
    val dictionaryValue: DictionaryValueEntity? = null,

    val sortOrder: String? = null,
    var testName: String = "Test"
) : AbstractEntity<UUID>(id)