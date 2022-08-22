# Enuygun&Patika Test Automation Bootcamp Final Homework

Proje 3 kısımdan oluşmaktadır. Bunlar:

1-Selenium ile Enuygun.com sitesinden uçak bileti alma işleminin otomasyonunun yapıldığı ve testlerinin yapıldığı kısım,

2-Ödev için verilen requestler doğrulutsunda api testlerinin yazıldığı kısım,

3-[patikaapiium.apk](https://github.com/malierbay9/enuygun-patika-bootcamp-final-homework/blob/develop/src/test/resources/app/patikaappium.apk) mobil uygulamasına ait otomasyon ve testlerin olduğu kısımlardır.

## 1- Flight Ticket Case

Bu kısıma ait dosyalar [flight_ticket_case] paketinin içindedir.

#### [util] Paketi

- [PropertyReader] sınıfı, [enuygun_config.yaml] dosyasından property okumayı sağlayan sınıftır. Okuduğu propertyleri [ConfigProperties] sınıfına çevirir.


- [ConfigProperties] sınıfından driver propertylerini elde ederiz.


- [DriverOptions] sınıfı, driver capabilitylerini kullanmak üzere elde ettiğimiz sınıftır.


- [DriverFactory] sınıfından ConfigProperties sınıfından elde ettiğimiz propertylere göre DriverOptions classından aldığımız capabilitylere sahip bir driver elde ederiz.


- [TestListener] sınıfı testlerin başlangıcı, bitişi ,başarılı ,başarısız olması gibi durumlarda çalışan metotlar içerir. Bu metotların içlerinde loglama ve raporlama işlemleri yapılır. Raporlar [test-output] dosyası içinde 'flight_case_reports.html' adıyla oluşan dosyayla görüntülenebilir.


### [pages] Paketi

Testlerde kullanılacak metotların ve bu metotlarda kullanılacak web elementlerin bulunduğu page object sınıflarını içeren pakettir.

- [BasePage] sınıfında kullanacağımız driverı DriverFactory sınıfından elde edip instance ederiz. Bu sınıf diğer page object sınıflarından extend edilir. Böylece burda instance ettiğimiz driver alt sınıflarda da kullanılabilir. Ayrıca bu sınıf , alt sınıflarda kolaylık sağlaması için kullanılan metotlar da içerir.


- [home_page] paketi;
    
    - [HomePage] sınıfı, uçak biletinin lokasyon ve tarih bilgilerini seçtirdiğimiz sayfaya ait element locatorlarının bir kısmını ve testlerde kullanılacak bu sayfayla ilgili metotları barındırır.
    - Bu sayfadaki element locatorlarının lokasyon seçimi ile ilgili olan kısmı [DestinationSection] sınıfında, tarih seçimi ile ilgili olan kısmı [DateSection] sınıfındadır.
    - Bu sınıflar HomePage sınıfında static olarak import edilerek HomePage sınıfında kullanılır.



- [flights_page] paketi;

    - [FlightsPage] sınıfı, önceden yapılan seçimlere göre var olan uçuşların görüntülendiği sayfayla ilgili metotların olduğu sınıftır.
    - Bu metotlarda kullanılan element locatorlarının , sayfanın filtre kısmıyla ilgili olanları [FilterSection] sınıfında, uçuş paketlerinin olduğu kısımla ilgili olanları [ResultsSection] sınıfındadır.
    - Bu sınıflar FligtsPage sınıfında static olarak import edilerek FlightsPage sınıfında kullanılır.


- [FligtInfoPage] sınıfı,  seçilen uçuşların bilgilerinin görüntülendiği sayfaya ait element locatorlarını ve testlerde kullanılacak metotları barındırır.


### [tests] Paketi

- [BaseTest] sınıfı, testlerde kullanılacak page nesnelerinin instance edilmesi , gerekli parametrelere değerlerin verilmesi gibi testlerin çalışmaya başlamasından önce yapılacak işlemler ve testler bittikten sonra driverın sonlandırılması işleminin yapıldığı sınıftır. Bu sınıf testlerin olduğu sınıftan extend edilerek kullanılır.


- [Tests] sınıfı, testlerin bulunduğu sınıftır.


## 2- Api Test Case

Bu kısıma ait dosyalar [api_case] paketi içindedir.

Proje **Rest Assured** kütüphanesi kullanılarak oluşturulmuştur.

### [requests] paketi

- [GroceryApiRequests] sınıfı, ödevde verilen;
   
    - GET/allGrocery
    - GET/allGrocery/{name}
    - POST/add
     sorgularını gerçekleştirip cevaplarını döndüren metotları içerir.
  

- [PetStoreApiRequests] sınıfı , petstore.swagger.io da bulunan;

![img_1.png](src/readme_images/img_1.png)
![img.png](src/readme_images/img.png)
sorgularını gerçekleştirilip cevaplarını döndüren metotları içerir.

### [test] paketi

- [BaseTest] sınıfı, testlerin çalışmasından önce testlerde kullanılacak request nesnelerinin instance edildiği sınıftır. Test sınıflarından extend ederek kullanılır.


- [GroceryApiTests] sınıfı, GroceryApiRequests sınıfındaki request metotlarının kullanıldığı testlerin  olduğu sınıftır.


- [PetStoreApiTests] sınıfı, PetStoreApiRequests sınıfındaki request metotlarının kullanırak yazıldığı testlerin olduğu sınıftır.


### [models] paketi

Bu paketteki sınıfları requestlerden dönen responselardaki bilgileri daha kolay elde etmek ya da requestimizde kullanacağımız 'body' yi daha kolay oluşturmak için kullandığımız sınıflardır.


Responseları ya da kullanacağımız body yi bu sınıfların nesnelerine çevirip , Response ların istediğimiz 'path' lerindeki değerlerini get metotlarıyla elde ederiz.

- [GroceryItem] sınıfı, Grocery api requestleri için kullanışmıştır.
- [Pet] sınıfı, petstore api requestleri için kullanılmıştır.

### [endpoints] paketi

- [GroceryEndPoints] sınıfı, Grocery requestlerinde kullanılacak endpointleri barındırır.
- [PetStoreEndPoints] sınıfı, petstore requestlerinde kullanılacak endpointleri barındırır.

### [api_listener] paketi
Bu paketteki [ApiTestListener] sınıfı , flight ticket case kısmındaki TestListener sınıfıyla aynı işlevi Api Tesleri için gerçekleştirir.

## 3- Appium Case
Bu kısıma ait dosyalar [appium_case] paketi içindedir.

### [utils] Paketi

-[CapabilitySettings] sınıfı, kullanacağımız android driver için gerekli capabilitylerin [testPhoneCaps.json] dosyasından okuyup DesiredCapabilites nesnesi haline getirilmesini sağlar.


-[DriverManager] sınıfı, kullanacağımız android driverı elde etmemizi sağlar.


-[AppiumLocalStarter] sınıfı, **Appium**u local olarak çalıştırmayı sağlar.


-[AndroidListener] sınıfı , flight ticket case kısmındaki TestListener sınıfıyla aynı işlevi Appium Tesleri için gerçekleştirir.


-[ExcelDataReader] sınıfı , [user_data.xlsx] excel dosyasından data okumayı sağlar. Okunan datayı [DP] classındaki @DataProvider anotasyonu bulunan metotla testlerimizde kullanırız.

### [pages] Paketi

- [BasePage] sınıfı diğer page object sınıflarından extend edilir. Bu sınıfın constructor metodunda driver parametre olarak alınır ve böylece alt classlarda kullanılabilir. Ayrıca PageFactory.initElements() metodu da bu constructor için kullanılır. Böylece alt sınıflarda tekrar tekrar kullanılmasına gerek kalmaz.


-[SignInPage] sınıfı, uygulamamızın giriş yap sayfasındaki elementler ve testlerde kullanacağımız metotları barındırır.


-[WelcomePage] sınıfı, uygulamamızda giriş yaptıktan sonra gelen hoş geldiniz sayfasındaki elementler ve testlerde kullanacağımız metotları barındırır.


-[AddCustomerPage] sınıfı, uygulamamızda yeni müşteri ekleme sayfasındaki elementler ve testlerde kullanacağımız metotları barındırır.


-[CustomersPage] sınıfı, uygulamamızda kayıtlı müşterilerin olduğu sayfadaki elementler ve testlerde kullanacağımız metotları barındırır.

### [tests] Paketi


- [BaseTest] sınıfı, testlerden önce testlerde kullanıcak driverın instance edildği ve page objectlerin instance edildiği, testlerde kullanılacak parametrelerin verildiği, testler bittikten sonra da driverın sonlandırıldığı sınıftır. Eğer Appium local starter aktif ise appium u program olarak ayrıca çalıştırmayınız.


- [Tests] sınıfı, mobil uygulamanın testlerinin olduğu sınıftır.

