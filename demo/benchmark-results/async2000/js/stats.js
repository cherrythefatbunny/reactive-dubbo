var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "60000",
        "ok": "59677",
        "ko": "323"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "102",
        "ko": "2"
    },
    "maxResponseTime": {
        "total": "468",
        "ok": "468",
        "ko": "171"
    },
    "meanResponseTime": {
        "total": "113",
        "ok": "114",
        "ko": "28"
    },
    "standardDeviation": {
        "total": "18",
        "ok": "16",
        "ko": "33"
    },
    "percentiles1": {
        "total": "112",
        "ok": "112",
        "ko": "13"
    },
    "percentiles2": {
        "total": "116",
        "ok": "116",
        "ko": "40"
    },
    "percentiles3": {
        "total": "125",
        "ok": "125",
        "ko": "101"
    },
    "percentiles4": {
        "total": "145",
        "ok": "145",
        "ko": "149"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 59677,
        "percentage": 99
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 323,
        "percentage": 1
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "722.892",
        "ok": "719",
        "ko": "3.892"
    }
},
contents: {
"req_async-0df93": {
        type: "REQUEST",
        name: "async",
path: "async",
pathFormatted: "req_async-0df93",
stats: {
    "name": "async",
    "numberOfRequests": {
        "total": "60000",
        "ok": "59677",
        "ko": "323"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "102",
        "ko": "2"
    },
    "maxResponseTime": {
        "total": "468",
        "ok": "468",
        "ko": "171"
    },
    "meanResponseTime": {
        "total": "113",
        "ok": "114",
        "ko": "28"
    },
    "standardDeviation": {
        "total": "18",
        "ok": "16",
        "ko": "33"
    },
    "percentiles1": {
        "total": "112",
        "ok": "112",
        "ko": "13"
    },
    "percentiles2": {
        "total": "116",
        "ok": "116",
        "ko": "40"
    },
    "percentiles3": {
        "total": "125",
        "ok": "125",
        "ko": "101"
    },
    "percentiles4": {
        "total": "145",
        "ok": "145",
        "ko": "149"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 59677,
        "percentage": 99
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 0,
        "percentage": 0
    },
    "group4": {
        "name": "failed",
        "count": 323,
        "percentage": 1
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "722.892",
        "ok": "719",
        "ko": "3.892"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
