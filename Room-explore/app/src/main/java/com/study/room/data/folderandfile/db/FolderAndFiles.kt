package com.study.room.data.folderandfile.folder.db

import androidx.room.Embedded
import androidx.room.Relation
import com.study.room.data.folderandfile.db.FileEntity
import com.study.room.data.folderandfile.db.FolderEntity

data class FolderAndFiles(
    @Embedded
    val folder: FolderEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentFolderId"
    )
    val files: List<FileEntity>
)
