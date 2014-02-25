package com.softwaremill.codebrag.domain

import org.joda.time.DateTime
import org.bson.types.ObjectId

case class CommitInfo(id: ObjectId, sha: String, message: String, authorName: String, authorEmail: String,
                      committerName: String, committerEmail: String, authorDate: DateTime,
                      commitDate: DateTime, parents: List[String])

object CommitInfo {
  def apply(sha: String, message: String, authorName: String, authorEmail: String,
            committerName: String, committerEmail: String, authorDate: DateTime,
            commitDate: DateTime, parents: List[String]) = {
    new CommitInfo(new ObjectId(), sha, message, authorName, authorEmail, committerName, committerEmail,
      authorDate, commitDate, parents)
  }

  implicit object CommitLikeCommitInfo extends CommitLike[CommitInfo] {
    def authorName(commitLike: CommitInfo) = commitLike.authorName
    def authorEmail(commitLike: CommitInfo) = commitLike.authorEmail
  }
}

case class CommitFileInfo(filename: String, status: String, patch: String)

trait CommitLike[T] {
  def authorName(commitLike: T): String
  def authorEmail(commitLike: T): String
}

/**
 * Commit info without all those files and diffs, only basic commit's data
 */
case class LightweightCommitInfo(id: ObjectId, sha: String, authorName: String, authorEmail: String, commitDate: DateTime)

object LightweightCommitInfo {
  def apply(commitInfo: CommitInfo) = {
    new LightweightCommitInfo(commitInfo.id, commitInfo.sha, commitInfo.authorName, commitInfo.authorEmail, commitInfo.commitDate)
  }

  implicit object CommitLikeLightweightCommitInfo extends CommitLike[LightweightCommitInfo] {
    def authorName(commitLike: LightweightCommitInfo) = commitLike.authorName
    def authorEmail(commitLike: LightweightCommitInfo) = commitLike.authorEmail
  }
}