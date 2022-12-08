package business.service;

import business.model.LibraryMember;
import business.service.impl.LibraryServiceImpl;

import java.util.Map;

public class LibraryMemberService extends AbstractLibraryService {

    public Map<String, LibraryMember> findAllLibraryMembers() {
        return dataAccess.readMemberMap();
    }

    public void addUpdateNewLibraryMember(LibraryMember libraryMember) {
        dataAccess.saveNewMember(libraryMember);
    }

    public void deleteLibraryMember(String memberId) {
        dataAccess.deleteMember(memberId);
    }
}
