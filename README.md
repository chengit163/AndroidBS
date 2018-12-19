# AndroidBS

相关下载  
[bzip2-1.0.6.tar.gz](https://fossies.org/linux/misc/bzip2-1.0.6.tar.gz)  
[bsdiff-4.3.tar.gz](http://www.daemonology.net/bsdiff/bsdiff-4.3.tar.gz)  

### 在Linux下或使用Windows10的Linux子系统

- 编译&&安装bzip2:  
make && make install  

- 修改bsdiff的Makefile:  
```
CFLAGS      +=  -O3 -lbz2
CC = gcc
PREFIX      ?=  /usr/local
INSTALL_PROGRAM ?=  ${INSTALL} -c -s -m 555
INSTALL_MAN ?=  ${INSTALL} -c -m 444

all:        bsdiff bspatch
bsdiff:     bsdiff.c
	$(CC) bsdiff.c $(CFLAGS) -o bsdiff
bspatch:    bspatch.c
	$(CC) bspatch.c $(CFLAGS) -o bspatch

install:
	${INSTALL_PROGRAM} bsdiff bspatch ${PREFIX}/bin
	.ifndef WITHOUT_MAN
	${INSTALL_MAN} bsdiff.1 bspatch.1 ${PREFIX}/man/man1
	.endif
```

- 查看文件md5命令  
md5sum