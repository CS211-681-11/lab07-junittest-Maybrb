package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("constructor ปกติ: score เริ่มต้น = 0")
    void defaultScoreIsZero() {
        Student s = new Student("1", "Maysa");
        assertEquals(0.0, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("constructor แบบกำหนด score: กำหนดได้ตามค่า input")
    void ctorWithScore() {
        Student s = new Student("1", "Maysa", 12.5);
        assertEquals(12.5, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("changeName: ตัดช่องว่างและเปลี่ยนชื่อเมื่อไม่ว่าง")
    void changeNameTrimmed() {
        Student s = new Student("1", "Old");
        s.changeName("   New Name  ");
        assertEquals("New Name", s.getName());
    }

    @Test
    @DisplayName("changeName: ไม่เปลี่ยนเมื่อชื่อว่าง/มีแต่ช่องว่าง")
    void changeNameEmptyIgnored() {
        Student s = new Student("1", "Keep");
        s.changeName("   ");
        assertEquals("Keep", s.getName());
    }

    @Test
    @DisplayName("addScore: เพิ่มค่าบวกครั้งเดียว")
    void addScoreOnce() {
        Student s = new Student("1", "A");
        s.addScore(45.15);
        assertEquals(45.15, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("addScore: เพิ่มหลายครั้งสะสมถูกต้อง")
    void addScoreMultiple() {
        Student s = new Student("1", "A");
        s.addScore(10);
        s.addScore(15.5);
        assertEquals(25.5, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("addScore: ค่าศูนย์หรือลบถูกเมิน (ตามโค้ด >0 เท่านั้นถึงเพิ่ม)")
    void addScoreNonPositiveIgnored() {
        Student s = new Student("1", "A");
        s.addScore(0);
        s.addScore(-7);
        assertEquals(0.0, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("grade: คะแนน 85 ได้ A")
    void gradeA() {
        Student s = new Student("1", "A");
        s.addScore(85);
        assertEquals("A", s.grade());
        assertEquals("A", s.getGrade()); // method proxy
    }

    @Test
    @DisplayName("isId: ตรง/ไม่ตรง")
    void isId() {
        Student s = new Student("6710405150", "Maysa");
        assertTrue(s.isId("6710405150"));
        assertFalse(s.isId("X"));
    }

    @Test
    @DisplayName("isNameContains: ไม่สนตัวพิมพ์ใหญ่เล็ก และเป็น substring")
    void isNameContains() {
        Student s = new Student("1", "Maysa Jet");
        assertTrue(s.isNameContains("mAy"));
        assertTrue(s.isNameContains("jet"));
        assertFalse(s.isNameContains("Chris"));
    }

    @Test
    @DisplayName("toString: มี id/name/score อยู่ในข้อความ")
    void toStringHasFields() {
        Student s = new Student("1", "A", 5);
        String t = s.toString();
        assertTrue(t.contains("id"));
        assertTrue(t.contains("name"));
        assertTrue(t.contains("score"));
    }
}
