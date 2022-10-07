Pojo
====

    public class SomePojo {
    private String firstName;
    private String lastName;

    public SomePojo(String fname, String lname) {
        this.firstName = fname;
        this.lastName = lname;
    }

    private String getFirstName() {
        return firstName;
    }

    public void setFname(String fname) {
        this.firstName = fname;
    }

    public String getLalstName() {
        return lastName;
    }

    public void setLastName(String lname) {
        this.lastName = lname;
    }}


Oluşturmuş olduğumuz bu java sınıfı framework’lerden bağımsız istenildiği gibi kullanılabilir. Başka herhangi bir nesne ile bağlantısı olmayan, bünyesinde `instance variable, constructor, getter, setter` ve `toString` gibi metodları barındırabilen en temel objeye POJO ismi verilir. Çok basit tanımlarsak eğer en temel Java nesnelerine verilen isim olarak belirtebiliriz. Aslında POJO’nun tam bir tanımının olmadığını söyleyebiliriz. Bazı kaynaklar bunun herhangi bir framework’e veya başka bir sınıfa bağımlılığı olmayan bir sınıf olarak tanımlıyor. Çok basit bir class oluşturup içerisine getter,setter ve constructor tanımladığımızı düşünelim. Artık bu sınıftan oluşturacağınız bir obje bizim için POJO’yu ifade ediyor diyebiliriz.

Başka bir örnek verecek olursak da örneğin bir API’den data çekiyorsunuz ve dönen datayı bir java objesi olarak tutmak istediniz. Yine en temel şekilde POJO’muzu oluşturup gelen dataları  `setter` metodları veya `constructor` ile class’ımızın ilgili değişkenlerine atayarak bir POJO obje oluşturabiliriz. POJO kavramını bu şekilde oldukça basit ve sade; en temel bir java objesi olarak düşünebilirsiniz.