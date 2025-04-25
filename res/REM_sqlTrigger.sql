
/* 회원가입 시 컨트롤러에서 회원 테이블에 INSERT */
--회원활동내역 테이블에 자동으로 RECORD 추가
--주소 테이블에 DEFAULT NULL값으로 자동 추가
CREATE OR REPLACE TRIGGER TRG_MEMBER_INSERT
    AFTER INSERT ON MEMBER
    FOR EACH ROW
BEGIN
    INSERT INTO MEMBER_PROGRESS (MEM_NO)
    VALUES (:NEW.MEM_NO);
    INSERT INTO ADDRESS (MEM_NO)
    VALUES (:NEW.MEM_NO);
END;

/* 페이 내역 신규 등록 시 - 충전, 거래시작/완료 */
--회원 테이블의 잔액 UPDATE
CREATE OR REPLACE TRIGGER SEQ_DEPOSIT_ACCOUNT_TRANSACTION_DETAILS_INSERT
    AFTER INSERT ON DEPOSIT_ACCOUNT_TRANSACTION_DETAILS
    FOR EACH ROW
BEGIN
    UPDATE MEMBER
       SET MEM_BAL = MEM_BAL + :NEW.PRICE
     WHERE MEM_NO = :NEW.TFR_MEM_NO;
END;

/* 구매자의 거래주문 완료 시 컨트롤러에서 거래 테이블에 INSERT */
--안심거래 테이블에 자동으로 INSERT
--페이 테이블에 구매자 출금 내역 INSERT
--판매자에게 알림 INSERT
--거래 주문 시 회원활동내역 업데이트
CREATE OR REPLACE TRIGGER trg_transaction_insert
    AFTER INSERT ON TRANSACTION
    FOR EACH ROW
BEGIN
    INSERT INTO SAFE_TRANSACTION (SAFE_NO, TXN_NO, SAFE_STATUS, SAFE_AT)
    VALUES (SEQ_SAFE_TRANSACTION.NEXTVAL, :NEW.TXN_NO, 0, CURRENT_TIMESTAMP);

    DECLARE
          v_prod_price NUMBER(8,0);
          v_prod_approach NUMBER(1,0);
    BEGIN
        SELECT PROD_PRICE
          INTO v_prod_price
          FROM PRODUCT
         WHERE PROD_NO = :NEW.PROD_NO;
        
        SELECT PROD_TR_APPROCH
          INTO v_prod_approach
          FROM PRODUCT
         WHERE PROD_NO = :NEW.PROD_NO;
       
        INSERT INTO DEPOSIT_ACCOUNT_TRANSACTION_DETAILS
        VALUES (SEQ_DEPOSIT_ACCOUNT_TRANSACTION_DETAILS.NEXTVAL,
            :NEW.MEM_NO, 0, v_prod_price*(-1), SYSTIMESTAMP,'구매자출금');
        
        IF v_prod_approach = 0 THEN
            UPDATE MEMBER_PROGRESS
               SET PROG_TRANS_REQUEST = PROG_TRANS_REQUEST +1,
                   PROG_TRANS_DIRECT = PROG_TRANS_DIRECT +1
             WHERE MEM_NO = :NEW.MEM_NO;
        ELSIF v_prod_approach = 1 THEN
            UPDATE MEMBER_PROGRESS
               SET PROG_TRANS_REQUEST = PROG_TRANS_REQUEST +1,
                   PROG_TRANS_SAFE = PROG_TRANS_SAFE +1
             WHERE MEM_NO = :NEW.MEM_NO;
        END IF;
    END;
END;

/* 구매자의 구매확정 클릭 시 거래 테이블의 상태값 2로 UPDATE */
--안심거래 테이블의 상태값 거래완료로 UPDATE
--페이 테이블에 판매자 입금 내역 자동으로 INSERT
CREATE OR REPLACE TRIGGER trg_transaction_update
    AFTER UPDATE OF TXN_STATUS ON TRANSACTION
    FOR EACH ROW
BEGIN
    UPDATE SAFE_TRANSACTION
       SET SAFE_STATUS = 1
     WHERE TXN_NO = :NEW.TXN_NO;
     
    IF :NEW.TXN_STATUS = 2 THEN
        DECLARE
           v_prod_price NUMBER(8,0); 
           v_prod_member NUMBER(5,0);
        BEGIN
            SELECT PROD_PRICE
              INTO v_prod_price
              FROM PRODUCT
             WHERE PROD_NO = :NEW.PROD_NO;
        
            SELECT MEM_NO
              INTO v_prod_member
              FROM PRODUCT
             WHERE PROD_NO = :NEW.PROD_NO;
             
            INSERT INTO DEPOSIT_ACCOUNT_TRANSACTION_DETAILS
            VALUES(SEQ_DEPOSIT_ACCOUNT_TRANSACTION_DETAILS.NEXTVAL, v_prod_member, 0,
                v_prod_price, SYSTIMESTAMP, '판매자입금');
       END;
    END IF;
END;


--/* 상품 등록 시 회원활동내역 업데이트 */
--CREATE OR REPLACE TRIGGER trg_product_insert
--    AFTER INSERT ON PRODUCT
--    FOR EACH ROW
--BEGIN
--    UPDATE MEMBER_PROGRESS
--       SET PROG_SELL = PROG_SELL + 1
--     WHERE MEM_NO = :NEW.MEM_NO;
--END;
--+>: PROG_SELL 컬럼은 내 상품이 판매 완료됐을 때 cnt하기로 변경할게요.

/* 리뷰 등록 시 회원활동내역 업데이트 */
CREATE OR REPLACE TRIGGER trg_review_insert
    AFTER INSERT ON REVIEW
    FOR EACH ROW
BEGIN
    DECLARE
        v_review_member NUMBER(5,0);
    BEGIN
        SELECT A.MEM_NO
          INTO v_review_member
          FROM TRANSACTION A
         INNER JOIN REVIEW B ON(B.TXN_NO = A.TXN_NO);
         
        UPDATE MEMBER_PROGRESS
           SET PROG_REVIEW = PROG_REVIEW + 1
         WHERE MEM_NO = v_review_member;
    END;
END;

/* 상품상세페이지 조회 시 회원활동내역 업데이트 */
CREATE OR REPLACE TRIGGER trg_viewcount_insert
    AFTER INSERT ON VIEWCOUNT
    FOR EACH ROW
BEGIN
    UPDATE MEMBER_PROGRESS
       SET PROG_VIEWCOUNT = PROG_VIEWCOUNT + 1
     WHERE MEM_NO = :NEW.MEM_NO;
END;

/* 뱃지 획득 시 회원활동내역 업데이트 */
CREATE OR REPLACE TRIGGER trg_member_achievements_insert
    AFTER INSERT ON MEMBER_ACHIEVEMENTS
    FOR EACH ROW
BEGIN
    UPDATE MEMBER_PROGRESS
       SET PROG_ACH_BADGES = PROG_ACH_BADGES + 1
     WHERE MEM_NO = :NEW.MEM_NO;
END;

/* 거래 요청 시 회원활동내역 업데이트 */
-->trg_transaction_insert에 기술

/* 문의글 작성 시 회원활동내역 업데이트 */
CREATE OR REPLACE TRIGGER trg_qna_board_insert
    AFTER INSERT ON QNA_BOARD
    FOR EACH ROW
BEGIN
    UPDATE MEMBER_PROGRESS
       SET PROG_QUESTION = PROG_QUESTION + 1
     WHERE MEM_NO = :NEW.MEM_NO;
END;

/* 신고글 작성 시 OR 신고 당할 시 회원활동내역 업데이트 */
CREATE OR REPLACE TRIGGER trg_report_board_insert
    AFTER INSERT ON REPORT_BOARD
    FOR EACH ROW
BEGIN
    UPDATE MEMBER_PROGRESS
       SET PROG_REPORTING = PROG_REPORTING + 1
    WHERE MEM_NO = :NEW.MEM_NO;
    
    IF :NEW.RPT_TYPE = 1 THEN
        UPDATE MEMBER_PROGRESS
           SET PROG_REPORTED = PROG_REPORTED + 1
         WHERE MEM_NO = :NEW.RPT_IDX_NO;
    END IF;
END;

/* 상품 등록 시 회원활동내역 업데이트 */
CREATE OR REPLACE TRIGGER trg_product_insert
    AFTER INSERT ON PRODUCT
    FOR EACH ROW
BEGIN
    UPDATE MEMBER_PROGRESS
       SET PROG_SELL = PROG_SELL + 1
     WHERE MEM_NO = :NEW.MEM_NO;
END;

/* 리뷰 등록 시 판매자의 리뷰 평점 업데이트 */
CREATE OR REPLACE TRIGGER trg_review_insert
    AFTER INSERT ON REVIEW
    FOR EACH ROW
BEGIN
    DECLARE
        v_rating_avg NUMBER(2,0);
        v_member_no NUMBER(5,0);
    BEGIN
        SELECT MEM_NO
          INTO v_member_no
          FROM TRANSACTION
         WHERE TXN_NO = :NEW.TXN_NO;
        
        SELECT AVG(R.REVIEW_RATING)
          INTO v_rating_avg
          FROM REVIEW R
         INNER JOIN TRANSACTION T ON(R.TXN_NO = T.TXN_NO)
         WHERE T.MEM_NO = v_member_no;
         
        UPDATE MEMBER
           SET PR_RATING_AVG = v_rating_avg
         WHERE MEM_NO = v_member_no;
    END;
END;