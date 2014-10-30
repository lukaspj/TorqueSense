package com.intellij.torquescript;

import com.intellij.openapi.diff.impl.patch.formove.PathMerger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.torquescript.psi.TSFile;
import com.intellij.torquescript.psi.TSFnDeclStmt;
import com.intellij.util.indexing.FileBasedIndex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lukas on 25-10-2014.
 */
public class TSUtil {
    public static List<TSFnDeclStmt> findProperties(Project project, String key) {
        List<TSFnDeclStmt> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TSFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for(VirtualFile virtualFile : virtualFiles) {
            TSFile tsFile = (TSFile) PsiManager.getInstance(project).findFile(virtualFile);
            if(tsFile == null)
                continue;
            TSFnDeclStmt[] fnDecls = PsiTreeUtil.getChildrenOfType(tsFile, TSFnDeclStmt.class);
            if(fnDecls == null)
                continue;
            for (TSFnDeclStmt fnDecl : fnDecls) {
                if(key.equals(fnDecl.getKey())) {
                    if(result == null) {
                        result = new ArrayList<TSFnDeclStmt>();
                    }
                    result.add(fnDecl);
                }
            }
        }
        return result != null ? result : Collections.<TSFnDeclStmt>emptyList();
    }

    public static List<TSFnDeclStmt> findProperties(Project project) {
        List<TSFnDeclStmt> result = new ArrayList<TSFnDeclStmt>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, TSFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            TSFile tsFile = (TSFile)PsiManager.getInstance(project).findFile(virtualFile);
            if(tsFile == null)
                continue;
            TSFnDeclStmt[] fnDecls = PsiTreeUtil.getChildrenOfType(tsFile, TSFnDeclStmt.class);
            if(fnDecls != null)
                Collections.addAll(result, fnDecls);
        }
        return result;
    }
}
