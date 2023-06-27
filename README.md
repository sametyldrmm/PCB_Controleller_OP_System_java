# PCB_Controleller_OP_System_java

## Informations
### PCB nedir ?
Process Control Block (PCB) (İşlem Denetim Bloğu) bir işletim sistemi kavramıdır ve her çalışan işlem için bir PCB oluşturulur. PCB, işlem hakkında bilgileri içeren bir veri yapısıdır ve işletim sistemi tarafından yönetilir. İşletim sistemi, PCB'leri kullanarak işlemi planlar, takip eder ve işlemi yönetir.

PCB, bir işlemin durumunu, kaynak kullanımını, öncelik düzeyini ve diğer önemli bilgileri tutar. İşte PCB'nin tipik olarak içerebileceği bazı bilgiler:
İşlem kimliği (PID): PCB, her işlem için bir benzersiz kimlik numarası içerir. PID, işletim sistemi tarafından işlemi tanımlamak ve yönetmek için kullanılır.
İşlem durumu: PCB, işlem durumunu takip eder. Örneğin, işlem çalışıyor, bekliyor veya sonlandırıldı durumlarından birini içerebilir.
Kaynaklar: PCB, işlemin kullanmakta olduğu kaynakları izler. Bu, bellek alanı, dosya açıkları, işlemci zaman dilimi ve diğer sistem kaynaklarını içerebilir.
Program sayaçları: İşlem, CPU'da hangi talimatın yürütüleceğini belirlemek için program sayaçlarını kullanır. PCB, işlem program sayaçlarının değerlerini saklar ve işlem sırası değiştirildiğinde kullanılır.
İşlem önceliği: İşlem, bir öncelik düzeyine sahip olabilir. PCB, işlem önceliğini tutar ve işletim sistemi, işlemleri öncelik sırasına göre planlar.
Giriş/çıkış bilgileri: İşlem, giriş/çıkış operasyonları yapabilir. PCB, işlem için yapılan giriş/çıkış işlemleriyle ilgili bilgileri içerebilir.
PCB'ler işletim sistemi çekirdeği tarafından oluşturulur ve yönetilir. İşletim sistemi, işlemleri oluştururken ve sonlandırırken PCB'leri günceller. PCB'ler işlem planlaması, kaynak tahsisi, zaman dilimi yönetimi ve hata ayıklama gibi işletim sistemi fonksiyonları için önemli bir rol oynar.
PCB'ler, çoklu işlemcili sistemlerde işlemciler arasında işlem geçişlerini yönetmek için de kullanılabilir. Bir işlemci, bir işlemi çalıştırmayı bitirdiğinde, ilgili PCB'yi diğer işlemciye aktararak işlem sırasını devredebilir.
PCB kavramı, işletim sistemi ve işlem yönetimi alanında temel bir kavramdır ve işletim sistemi tasarımı ve uygulamasında büyük bir rol oynar.

Ödev için detayları PDF ten okuyabilirsiniz. Daha sonra düzenlenecektir.

###### Bu konudakki kaynak çok aşırı sınırlı olduğu için elde ettiğim deneyimleri direk aktarmak insanları yanlış yönlendirebilir. Onay aldıktan sonra detaylı bir paylaşım yapılacaktır.

## Ödevi internette denk gelip hoşuma gittiği için yaptım Bartın üniversitesiyle bir ilgim yoktur!!!!

### Şuan görünen outputta satır 27 den sonrası hatalıdır.
![ Örnek bir output ](https://github.com/sametyldrmm/PCB_Controleller_OP_System_java/blob/master/Output.png)

### Sistemin genel çalışma prensibi aşşadaki fotoğraf gibidir
![Sistemin genel yapısı](https://github.com/sametyldrmm/PCB_Controleller_OP_System_java/blob/master/System_template.jpeg)

### Nasıl çalıştırılır
```
cd PCB_CONTROLLER_OOP
javac *.java
java PCB_CONTROLLER_OOP.Main
```
