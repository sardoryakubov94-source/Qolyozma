# ✍️ Qoʻlyozma

Oddiy matn va ilmiy maqolalarni **sharikli ruchkada qoʻlda yozilgandek** koʻrinishga keltiruvchi bepul veb-ilova. Hammasi brauzerda ishlaydi — matn hech qanday serverga yuborilmaydi.

## Imkoniyatlar

- Har bir harf alohida chiziladi: egilish, balandlik, oraliq va bosim har xil
- **Printer uchun realistik siyoh izi:** notekis qirralar, qirralarda siyoh quyuqlashishi, sharik ruchkaning siyoh oʻtkazmay qoladigan joylari
- 3 xil ruchka: sharikli, gel, qalam
- Siyoh rangi: tayyor ranglar + istalgan rang, **aralash rang (ikkinchi ton)** va aralashish darajasi
- **Printer tuzatish** slideri, qora-oq printer rejimi va 5 darajali **sinov varagʻi** (PDF)
- 49 ta yozuv uslubi (kirill + lotin, real qoʻlyozma, kursiv, marker)
- Ilmiy matn: `# Sarlavha`, `x^2`, `H_2O`, `C_{12}`, `x^{n+1}`
- Qogʻoz: chiziqli, daftar, katakli, nuqtali, oq; A4 / A5 / Letter
- Eksport: koʻp sahifali PDF (300 / 200 DPI) va har bir sahifa PNG

## Ishga tushirish

Hech narsa oʻrnatish shart emas. `index.html` ni brauzerda oching.

> Internet kerak: yozuv shriftlari Google Fonts’dan, PDF kutubxonasi (jsPDF) cdnjs’dan yuklanadi.

## GitHub Pages orqali joylash

1. GitHub’da yangi repozitoriy oching (masalan, `qolyozma`).
2. Shu fayllarni yuklang (`index.html`, `README.md`, `LICENSE`, `.gitignore`, `.nojekyll`).
3. **Settings → Pages → Build and deployment → Source: Deploy from a branch → Branch: `main` / `(root)` → Save.**
4. 1–2 daqiqadan keyin sayt `https://<foydalanuvchi>.github.io/qolyozma/` manzilida ochiladi.

Terminal orqali:

```bash
git init
git add .
git commit -m "Qoʻlyozma: dastlabki versiya"
git branch -M main
git remote add origin https://github.com/<foydalanuvchi>/qolyozma.git
git push -u origin main
```

## Printerda chop etish

- PDF ni **100% masshtabda** ("Actual size") chop eting, "Fit to page" ni oʻchiring.
- Rangli printerda "Toza" koʻrinish va 300 DPI tanlang.
- Printer rangni oʻzgartirishi mumkin (RGB → CMYK). **Sinov varagʻi**ni chop eting va eng yaxshi chiqqan qatorga qarab "Printer tuzatish"ni sozlang.
- Qora siyoh uchun printer sozlamasida "Grayscale" ni yoqing.

## Sozlash

Hammasi bitta `index.html` ichida:

- `FG` — yozuv uslublari ro‘yxati (Google Fonts nomlari). Yangi shrift qoʻshish uchun nomini mos guruhga yozing. Kirill harflarini qoʻllaydigan shriftlar `FG` birinchi guruhida.
- `PEN` — ruchka turlari: xiralik (`b`), qirra notekisligi (`k`), siyoh oʻtkazmasligi (`s`), chetda quyuqlashish (`p`), shaffoflik (`a`), yoʻgʻonlik (`t`).
- `INKS` — tayyor siyoh ranglari (RGB).

## Eslatma

Bu vosita formatlash va dizayn uchun moʻljallangan. Agar oʻqituvchi yoki tashkilot qoʻlda yozishni (yozganingizni tekshirish uchun) talab qilsa, vositadan oʻzingiz yozish oʻrnida foydalanmang.

## Litsenziya

[MIT](LICENSE)
