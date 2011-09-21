package org.jasoet.mandiri.domain;

import org.hibernate.annotations.Index;
import org.jasoet.mandiri.util.enums.UserStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@Cacheable(value = false)
public class User implements DomainObject, UserDetails {
    /*------------------------------ Fields ------------------------------*/
    @Id
    @GeneratedValue
    private Long id;

    @Index(name = "user_username_idx")
    @NotNull
    @Size(min = 5, max = 15)
    @Column(length = 15, nullable = false)
    private String username;
    @NotNull
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 41)
    private String salt;
    @NotNull
    @Column(length = 65, nullable = false)
    private String nama;
    @NotNull
    @Column(length = 65, nullable = false)
    private String email;
    @NotNull
    @Column(length = 255, nullable = false)
    private String alamat;
    @NotNull
    @Column(length = 25, nullable = false)
    private String kota;
    @NotNull
    @Column(length = 15, nullable = false)
    private String telp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Bank bank;
    @Index(name = "user_nomor_rekening_idx")
    @NotNull
    @Column(length = 35, nullable = false)
    private String nomorRekening;
    @NotNull
    @Column(length = 65, nullable = false)
    private String namaRekening;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd MMMM yyyy")
    private Date tanggalBergabung;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd MMMM yyyy")
    private Date tanggalTransfer;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd MMMM yyyy")
    private Date tanggalAktivasi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Staff staff;

    @Index(name = "user_sponsor_idx")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private User sponsor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Paket paket;

    @Column(nullable = false)
    private int nilaiDeposit;

    @Column(nullable = false)
    private int nilaiTransfer;

    private UserStatus status;

    private boolean enabled;

    @Transient
    private boolean accountNonExpired = true;

    @Transient
    private boolean accountNonLocked = true;

    @Transient
    private boolean credentialsNonExpired = true;

    @Column(nullable = false, columnDefinition = "int(2) default 1")
    private int jumlahProfit;

    public int getNilaiTransfer() {
        return nilaiTransfer;
    }

    public void setNilaiTransfer(int nilaiTransfer) {
        this.nilaiTransfer = nilaiTransfer;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJumlahProfit() {
        return jumlahProfit;
    }

    public void setJumlahProfit(int jumlahProfit) {
        this.jumlahProfit = jumlahProfit;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNilaiDeposit() {
        return nilaiDeposit;
    }

    public void setNilaiDeposit(int nilaiDeposit) {
        this.nilaiDeposit = nilaiDeposit;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

/* --------------------- Getter and Setter ---------------------*/

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User getSponsor() {
        return sponsor;
    }

    public void setSponsor(User sponsor) {
        this.sponsor = sponsor;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Date getTanggalAktivasi() {
        return tanggalAktivasi;
    }

    public void setTanggalAktivasi(Date tanggalAktivasi) {
        this.tanggalAktivasi = tanggalAktivasi;
    }

    public Date getTanggalBergabung() {
        return tanggalBergabung;
    }

    public void setTanggalBergabung(Date tanggalBergabung) {
        this.tanggalBergabung = tanggalBergabung;
    }

    public Date getTanggalTransfer() {
        return tanggalTransfer;
    }

    public void setTanggalTransfer(Date tanggalTransfer) {
        this.tanggalTransfer = tanggalTransfer;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getDataInArray() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String[] result = new String[10];
        result[0] = this.username;
        result[1] = this.nama;
        result[2] = this.nomorRekening;
        result[3] = this.namaRekening;
        result[4] = this.nilaiTransfer + "";
        result[5] = this.paket != null ? this.paket.getNama() : "";
        result[6] = sdf.format(this.getTanggalBergabung());
        result[7] = this.getTanggalAktivasi() != null ? sdf.format(this.getTanggalAktivasi()) : "";
        result[8] = this.status.name();
        result[9] = Long.toString(this.id);
        return result;
    }
// --------------------- Interface UserDetails ---------------------

    /* --------------------- Interface UserDetails --------------------- */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return status.name();
            }
        };

        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        authorityList.add(authority);
        return authorityList;
    }
}

