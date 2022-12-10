package mpp.business.service;

import mpp.business.model.LibraryMember;

import java.util.Map;

public class LibraryMemberService extends AbstractLibraryService {

    public Map<String, LibraryMember> findAllLibraryMembers() {
        return dataAccess.readMemberMap();
    }

    public void addUpdateLibraryMember(LibraryMember libraryMember) {
        dataAccess.saveNewMember(libraryMember);
    }

    public void deleteLibraryMember(String memberId) {
        dataAccess.deleteMember(memberId);
    }
}
