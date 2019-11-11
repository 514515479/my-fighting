const arrr = ['年', '个月', '星期', '天', '小时', '分钟', '秒'];
const arrn = [31536000, 2592000, 604800, 86400, 3600, 60, 1];

export default {
    // 将时间转化成离现在几分钟，几小时的格式
    timestampAgo(timestamp) {
        const date = new Date(timestamp);
        const diffMs = Date.now() - timestamp;//相差的毫秒数
        const diff = (diffMs) / 1000 / 60 / 60;  //相差的时间 小时数
        if (diff >= 24) {  //时间差大于24个小时 显示标准时间 例：2019.6.5 00:26:11
            const year = date.getFullYear();
            const month = date.getMonth() + 1;
            const day = date.getDate();
            let hours = date.getHours();
            if (hours < 10)
                hours = '0' + hours;
            let minutes = date.getMinutes();
            if (minutes < 10)
                minutes = '0' + minutes;
            let seconds = date.getSeconds();
            if (seconds < 10)
                seconds = '0' + seconds;
            return year + '.' + month + '.' + day + ' ' + hours + ':' + minutes + ':' + seconds;
        } else { //小于24小时 显示 时间差 例：2小时前

            const mistiming = Math.round((diffMs) / 1000);
            for (let i = 0; i < arrn.length; i++) {
                const inm = Math.floor(mistiming / arrn[i]);
                if (arrr[i] === arrr[6]) {
                    if (inm < 1) {
                        return '刚刚';
                    }
                }
                if (inm !== 0)
                    return inm + arrr[i] + '前';
            }
        }
    },
    // 将时间转化成离现在几分钟，几小时的格式
    timeStrAgo(timeStr) {
        const date = new Date(timeStr);
        const diffMs = Date.now() - date;//相差的毫秒数
        const diff = (diffMs) / 1000 / 60 / 60;  //相差的时间 小时数
        if (diff >= 24) {  //时间差大于24个小时 直接返回
            return timeStr;
        } else { //小于24小时 显示 时间差 例：2小时前
            const mistiming = Math.round((diffMs) / 1000);
            for (let i = 0; i < arrn.length; i++) {
                const inm = Math.floor(mistiming / arrn[i]);
                if (arrr[i] === arrr[6]) {
                    if (inm < 1) {
                        return '刚刚';
                    }
                }
                if (inm !== 0)
                    return inm + arrr[i] + '前';
            }
        }
    }
}
