package edu.axboot.domain;

import edu.axboot.domain.code.QCommonCode;
import edu.axboot.domain.education.book.QEducationBook;
import edu.axboot.domain.file.QCommonFile;
import edu.axboot.domain.program.QProgram;
import edu.axboot.domain.program.menu.QMenu;
import edu.axboot.domain.reservation.QResInfo;
import edu.axboot.domain.standard.QGuestInfo;
import edu.axboot.domain.standard.QRoomInfo;
import edu.axboot.domain.user.QUser;
import edu.axboot.domain.user.auth.QUserAuth;
import edu.axboot.domain.user.auth.menu.QAuthGroupMenu;
import edu.axboot.domain.user.role.QUserRole;
import com.chequer.axboot.core.domain.base.AXBootBaseService;
import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

import edu.axboot.domain.education.QEducationTeach;

import java.io.Serializable;


public class BaseService<T, ID extends Serializable> extends AXBootBaseService<T, ID> {

    protected QUserRole qUserRole = QUserRole.userRole;
    protected QAuthGroupMenu qAuthGroupMenu = QAuthGroupMenu.authGroupMenu;
    protected QCommonCode qCommonCode = QCommonCode.commonCode;
    protected QUser qUser = QUser.user;
    protected QProgram qProgram = QProgram.program;
    protected QUserAuth qUserAuth = QUserAuth.userAuth;
    protected QMenu qMenu = QMenu.menu;
    protected QCommonFile qCommonFile = QCommonFile.commonFile;

    protected QEducationTeach qEducationTeach = QEducationTeach.educationTeach;
    protected QEducationBook qEducationBook = QEducationBook.educationBook;
    protected QRoomInfo qRoomInfo = QRoomInfo.roomInfo;
    protected QResInfo qResInfo = QResInfo.resInfo;
    protected QGuestInfo qGuestInfo = QGuestInfo.guestInfo;

    protected AXBootJPAQueryDSLRepository<T, ID> repository;

    public BaseService() {
        super();
    }

    public BaseService(AXBootJPAQueryDSLRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }
}
