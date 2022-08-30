package com.study.room.data.folderandfile.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * ForeignKey 를 설정해서 Folder 의 삭제에도 그에 속한 files 의 삭제가 안되는 오류를 해결 가능
 */
@Entity(
    tableName = "files",
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("parentFolderId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val parentFolderId: Int
) {
    constructor(name: String, parentFolderId: Int) : this(0, name, parentFolderId)
}
