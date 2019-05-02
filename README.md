 - ### [Türkçe](#yazıyla-yazılmış-numaraları-rakamlı-sayıya-çevir)
 - ### [English](#convert-numbers-written-as-text-to-numbers)
# Yazıyla Yazılmış Numaraları Rakamlı Sayıya Çevir
Bu mini Java kodu, yazıyla -string- girilmiş bir sayıyı rakama çevirmeye yarar. Örneğin:
>sekizyüzdoksanbeşbindörtyüziki 

input'u
>895402

output'una dönüşür.
#
Yazıyla yazılmış sayıda, kelimeler arası boşluk olup olmaması herhangi bir fark yaratmaz. Kod temel olarak basamak veya bir rakamı gösteren kelimeler arasına boşluk koyarak output'u oluşturuyor.
>sekizyüzdoksanbeşbindörtyüziki 

veya
>sekiz yüz doksan beş bin dört yüz iki 

aynı sonucu verecektir:
>895402
## Sorunlar

 - Kod şu anda 1000'den büyük ve 1 milyardan küçük sayılar arasında çalışıyor.
 - Rastladığınız sorunları issue açarak belirtebilirsiniz.
#  Convert Numbers Written as Text to Numbers

This implementation allows you to convert numbers written as text to numbers (it works for Turkish text but it can be converted for English easily). 
>sekizyüzdoksanbeşbindörtyüziki 

becomes 
>895402
#
The implementation works even if the text has spaces between words or not, but, it does not correct typos.
>sekizyüzdoksanbeşbindörtyüziki 
>sekizyüzdoksan beşbindört yüziki 
>
or
>sekiz yüz doksan beş bin dört yüz iki 

gives same output
>895402


## Problems

 - It only works for numbers between greater than 1000 and less than 1 billion.
 - If you find any bugs, feel free to raise an issue.

