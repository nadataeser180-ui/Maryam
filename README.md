# 📱 Maryam - Data Collector App

تطبيق جوال لجمع المعلومات من الروابط والبريد الإلكتروني

## 📋 المتطلبات
- Android Studio
- Android SDK 21+
- Java 11+

## 🚀 كيفية البدء

### 1. استنساخ المشروع
```bash
git clone https://github.com/nadataeser180-ui/Maryam.git
cd Maryam
```

### 2. فتح في Android Studio
- افتح Android Studio
- اختر: File → Open
- اختر مجلد Maryam
- انتظر تحميل Gradle

### 3. تشغيل التطبيق
- اضغط على زر التشغيل (Play Button)
- أو اضغط Shift + F10 على لوحة المفاتيح

## 📱 المميزات
- ✅ إدخال الروابط والبريد الإلكتروني
- ✅ جمع البيانات تلقائياً من الروابط
- ✅ حفظ البيانات محلياً
- ✅ عرض البيانات المجمعة
- ✅ واجهة بسيطة وسهلة الاستخدام

## 🛠️ التقنيات المستخدمة
- Kotlin
- Android Architecture Components
- Room Database
- Jsoup (لجلب البيانات من الويب)
- Coroutines

## 📂 هيكل المشروع
```
Maryam/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/maryam/datacollector/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── data/
│   │   │   │   │   ├── DataEntity.kt
│   │   │   │   │   └── DataDao.kt
│   │   │   │   ├── database/
│   │   │   │   │   └── AppDatabase.kt
│   │   │   │   └── utils/
│   │   │   │       └── DataCollector.kt
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       │   └── activity_main.xml
│   │   │       └── values/
│   │   │           └── strings.xml
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
└── README.md
```

## 🎯 خطوات الاستخدام

### إضافة رابط جديد:
1. افتح التطبيق
2. ادخل الرابط في حقل الإدخال
3. اضغط على زر "جمع البيانات"
4. ستظهر البيانات المجمعة

### عرض البيانات:
1. البيانات المجمعة تظهر في قائمة أسفل الشاشة
2. كل عنصر يحتوي على:
   - عنوان الصفحة
   - الوصف
   - تاريخ الإضافة

### حذف البيانات:
1. اسحب العنصر لليسار أو اليمين
2. أو اضغط طويلاً على العنصر واختر حذف

## 🔧 التعديلات والتطويرات

يمكنك تعديل الكود من:
- `MainActivity.kt` - الشاشة الرئيسية
- `DataCollector.kt` - وظيفة جمع البيانات
- `activity_main.xml` - تصميم الواجهة

## 📞 المساعدة

إذا واجهت أي مشكلة:
1. تأكد من اتصالك بالإنترنت
2. افحص إصدار Android SDK
3. اضغط على Sync Now في Android Studio

---

**تم الإنشاء بواسطة:** Copilot
**الإصدار:** 1.0
