-----------------------------------�������---------------------------------------------
CREATE TABLE Channel_dt (
    channelCode NVARCHAR2(28) PRIMARY KEY,
    channelName NVARCHAR2(255)
);

CREATE TABLE ChannelHasUser_dt (
    channelList nvarchar2(255),
    channelCode NVARCHAR2(28),
    userId NVARCHAR2(100),
    channelName nvarchar2(255)
);

-----------------------------------ä�÷���---------------------------------------------
CREATE TABLE roomChannel (
    roomCode NVARCHAR2(28) PRIMARY KEY,
    roomName NVARCHAR2(255),
    channelCode NVARCHAR2(30)
);
-- ä�� �ڵ带 ������ �ִٸ� ���ڵ�� ���� ���̰� �����
-- ä���� �����ڴ� �߰� ������ �����ϰ� ���� or �߰� �ִ�ġ ����
CREATE TABLE roomUser_dt (
    roomList nvarchar2(255),
    roomName NVARCHAR2(255),
    roomCode nvarchar2(30),
    channelCode NVARCHAR2(28)
);
-----------------------------------ä�ñ��---------------------------------------------


CREATE TABLE chat_dt (
    roomCode NVARCHAR2(28),
    name NVARCHAR2(100) ,
    userId NVARCHAR2(100),
    channelCode NVARCHAR2(28),
    chatData clob,
    chatDate TIMESTAMP
);
commit;


