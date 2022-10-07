*Genel Kullanılanlar*
=====================

>`equalTo(X)` - used to check whether an actual element value is equal to a pre-specified expected element value
>>gerçek bir öğe değerinin önceden belirlenmiş bir beklenen öğe değerine eşit olup olmadığını kontrol etmek için kullanılır

>`hasItem("value")` - used to see whether a collection of elements contains a specific pre-specified item value
>> bir öğe koleksiyonunun önceden belirlenmiş belirli bir öğe değeri içerip içermediğini görmek için kullanılır

>`hasSize(3)` - used to verify the actual number of elements in a collection
>>bir koleksiyondaki gerçek eleman sayısını doğrulamak için kullanılır

>`not(equalTo(X))` - inverts any given matcher that exists in the Hamcrest
>>Hamcrest'te bulunan herhangi bir eşleştiriciyi tersine çevirir


*Number Related Assertions - Sayıyla İlgili Olan Kullanımlar*
=============================================================

>`equalTo` – It checks whether the retrieved number from response is equal to the expected number.
>>Yanıttan alınan sayının beklenen sayıya eşit olup olmadığını kontrol eder.

>`greaterThan` – checks extracted number is greater than the expected number.
>>çıkarılan sayının beklenen sayıdan büyük olup olmadığını kontrol eder.

>`greaterThanOrEqualTo` – checks whether the extracted number is greater than equal to the expected number.
>>ayıklanan sayının beklenen sayıdan büyük olup olmadığını kontrol eder.

>`lessThan` – It checks whether the retrieved number from response is lesser than to the expected number.
>>Yanıttan alınan sayının beklenen sayıdan küçük olup olmadığını kontrol eder.

>`lessThanOrEqualTo` – It checks whether the retrieved number from response is lesser than or equal to the expected number.
>>Yanıttan alınan sayının beklenen sayıdan küçük veya ona eşit olup olmadığını kontrol eder.


*String Related Assertions - String Dizesiyle Alakalı Kullanımlar*
==================================================================
>`equalTo` – It checks whether the extracted string from JSON is equal to the expected string.
>>JSON'dan çıkarılan dizenin beklenen dizeye eşit olup olmadığını kontrol eder.

>`equalToIgnoringCase` – It checks whether the extracted string from JSON is equal to the expected string without considering the case (small or capital).
>>JSON'dan çıkarılan dizenin, büyük/küçük harf dikkate alınmadan beklenen dizeye eşit olup olmadığını kontrol eder.

>`equalToIgnoringWhiteSpace` – It checks whether the extracted string from JSON is equal to the expected string by considering the white spaces.
>>Beyaz boşlukları dikkate alarak JSON'dan çıkarılan dizenin beklenen dizeye eşit olup olmadığını kontrol eder.

>`containsString` – It checks whether the extracted string from JSON contains the expected string as a substring.
>>JSON'dan ayıklanan dizenin bir alt dize olarak beklenen dizeyi içerip içermediğini kontrol eder.

>`startsWith` – It checks whether the extracted string from JSON is starting with a given string or character.
>>JSON'dan çıkarılan dizenin belirli bir dize veya karakterle başlayıp başlamadığını kontrol eder.

>`endsWith` – It checks whether the extracted string from JSON is ending with a given string or character.
>>JSON'dan çıkarılan dizenin belirli bir dize veya karakterle bitip bitmediğini kontrol eder.

