package ku.cs.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {

    private StudentList list;

    @BeforeEach
    void setup() {
        list = new StudentList();
        list.addNewStudent("1", "Alice", 40);
        list.addNewStudent("2", "Bob", 75);
        list.addNewStudent("3", "mAySa", 82);
    }

    @Test
    @DisplayName("addNewStudent(id,name): ตัดช่องว่างและเพิ่มเมื่อ id ใหม่")
    void addNewStudentTrimAndAdd() {
        StudentList l = new StudentList();
        l.addNewStudent("   10  ", "  New Kid  ");
        Student s = l.findStudentById("10");
        assertNotNull(s);
        assertEquals("New Kid", s.getName());
        assertEquals(0.0, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("addNewStudent(id,name): ไม่เพิ่มเมื่อ id ซ้ำ")
    void addNewStudentDuplicateIgnored() {
        int before = list.getStudents().size();
        list.addNewStudent("1", "Other");
        assertEquals(before, list.getStudents().size());
    }

    @Test
    @DisplayName("addNewStudent(id,name,score): สร้างพร้อมคะแนน")
    void addNewStudentWithScore() {
        list.addNewStudent("9", "Nine", 9.5);
        Student s = list.findStudentById("9");
        assertNotNull(s);
        assertEquals(9.5, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("addNewStudent: ไม่เพิ่มถ้า id หรือ name ว่างหลัง trim")
    void addNewStudentEmptyIgnored() {
        int before = list.getStudents().size();
        list.addNewStudent("   ", "Name");
        list.addNewStudent("ID", "   ");
        assertEquals(before, list.getStudents().size());
    }

    @Test
    @DisplayName("findStudentById: เจอ/ไม่เจอ")
    void findStudentById() {
        assertNotNull(list.findStudentById("1"));
        assertNull(list.findStudentById("999"));
    }

    @Test
    @DisplayName("filterByName: ไม่สนตัวพิมพ์ใหญ่เล็ก (คืน Student ตัวเดิม)")
    void filterByNameCaseInsensitive() {
        StudentList filtered = list.filterByName("mAy");
        ArrayList<Student> fs = filtered.getStudents();
        assertEquals(1, fs.size());
        Student original = list.findStudentById("3");
        assertSame(original, fs.get(0)); // อ้างอิง object เดียวกัน
    }

    @Test
    @DisplayName("filterByName: ไม่เจอแล้วได้ลิสต์ว่าง")
    void filterByNameEmpty() {
        StudentList filtered = list.filterByName("nope");
        assertTrue(filtered.getStudents().isEmpty());
    }

    @Test
    @DisplayName("giveScoreToId: ให้คะแนนกับ id ที่มีอยู่เท่านั้น (และต้องเป็นค่าบวก)")
    void giveScoreToId() {
        Student s = list.findStudentById("2");
        double before = s.getScore(); // 75
        list.giveScoreToId("2", 5);   // +5
        list.giveScoreToId("2", -10); // เมิน (addScore > 0 เท่านั้น)
        list.giveScoreToId("999", 50); // ไม่ทำอะไร
        assertEquals(before + 5, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("viewGradeOfId: คืนเกรดของ id ที่มีอยู่ / ไม่เจอคืน null")
    void viewGradeOfId() {
        assertEquals("A", list.viewGradeOfId("3"));   // 82 → A
        assertNull(list.viewGradeOfId("999"));
    }

    @Test
    @DisplayName("getStudents: มีสมาชิกตามที่เพิ่ม")
    void getStudents() {
        assertEquals(3, list.getStudents().size());
    }
}
